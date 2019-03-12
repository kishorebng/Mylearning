package com.kishore.apparchitecture.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.kishore.apparchitecture.AppExecutors
import com.kishore.apparchitecture.Utility.WeatherDateUtils
import com.kishore.apparchitecture.model.database.WeatherDao
import com.kishore.apparchitecture.model.database.WeatherTable
import com.kishore.apparchitecture.model.forview.ListWeatherEntry
import com.kishore.apparchitecture.model.network.WeatherNetworkDataSource
import java.util.*


class WeatherRepository(weatherDao: WeatherDao,
                        weatherNetworkDataSource: WeatherNetworkDataSource,
                        executors: AppExecutors) {

    private val LOG_TAG = WeatherRepository::class.java.getSimpleName()
    private lateinit var mWeatherDao: WeatherDao


    // For Singleton instantiation
    private lateinit var mWeatherNetworkDataSource: WeatherNetworkDataSource
    private lateinit var mExecutors: AppExecutors
    private var mInitialized = false

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: WeatherRepository? = null

        fun getInstance(weatherDao: WeatherDao,
                        weatherNetworkDataSource: WeatherNetworkDataSource,
                        executors: AppExecutors) =
                instance ?: synchronized(this) {
                    instance ?: WeatherRepository(weatherDao,weatherNetworkDataSource,executors).also { instance = it }
                }

    }

    init {

        mWeatherDao = weatherDao
        mWeatherNetworkDataSource = weatherNetworkDataSource
        mExecutors = executors

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        val networkData = mWeatherNetworkDataSource.getCurrentWeatherForecasts()
        networkData.observeForever { newForecastsFromNetwork ->
            mExecutors.diskIO().execute {
                // Deletes old historical data
                deleteOldData()
                Log.d(LOG_TAG, "Old weather deleted")
                // Insert our new weather data into Sunshine's database
                mWeatherDao.bulkInsert(*newForecastsFromNetwork)
                Log.d(LOG_TAG, "New values inserted")
            }
        }
    }
    /**
     * Creates periodic sync tasks and checks to see if an immediate sync is required. If an
     * immediate sync is required, this method will take care of making sure that sync occurs.
     */
    @Synchronized
    private fun initializeData() {

        // Only perform initialization once per app lifetime. If initialization has already been
        // performed, we have nothing to do in this method.
        if (mInitialized) return
        mInitialized = true

        // This method call triggers Sunshine to create its task to synchronize weather data
        // periodically.
        mWeatherNetworkDataSource.scheduleRecurringFetchWeatherSync()

        mExecutors.diskIO().execute {
            if (isFetchNeeded()) {
                startFetchWeatherService()
            }
        }
    }


    fun getCurrentWeatherForecasts(): LiveData<List<ListWeatherEntry>> {
        initializeData()
        val today = WeatherDateUtils.getNormalizedUtcDateForToday()
        return mWeatherDao.getCurrentWeatherForecasts(today)
    }

    fun getWeatherByDate(date: Date): LiveData<WeatherTable> {
        initializeData()
        return mWeatherDao!!.getWeatherByDate(date)
    }

    /**
     * Deletes old weather data because we don't need to keep multiple days' data
     */
    private fun deleteOldData() {
        val today = WeatherDateUtils.getNormalizedUtcDateForToday()
        mWeatherDao!!.deleteOldWeather(today)
    }

    /**
     * Checks if there are enough days of future weather for the app to display all the needed data.
     *
     * @return Whether a fetch is needed
     */
    private fun isFetchNeeded(): Boolean {
        val today = WeatherDateUtils.getNormalizedUtcDateForToday()
        val count = mWeatherDao!!.countAllFutureWeather(today)
        return count < WeatherNetworkDataSource.NUM_DAYS
    }

    /**
     * Network related operation
     */

    private fun startFetchWeatherService() {
        mWeatherNetworkDataSource.startFetchWeatherService()
    }

}