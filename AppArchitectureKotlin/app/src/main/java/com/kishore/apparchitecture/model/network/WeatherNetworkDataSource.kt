package com.kishore.apparchitecture.model.network

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.firebase.jobdispatcher.*
import com.kishore.apparchitecture.AppExecutors
import com.kishore.apparchitecture.model.database.WeatherTable
import com.kishore.apparchitecture.model.network.retrofit.*
import com.kishore.apparchitecture.model.network.retrofit.responsepojo.WeatherData
import com.kishore.apparchitecture.model.network.retrofit.rxjava.WeatherCoroutineJavaRetrofitClient
import com.kishore.apparchitecture.model.network.retrofit.rxjava.WeatherRxJavaRetrofitClient
import com.kishore.apparchitecture.model.network.service.WeatherFirebaseJobService
import com.kishore.apparchitecture.model.network.service.WeatherSyncIntentService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.util.concurrent.TimeUnit

class WeatherNetworkDataSource(context: Context, executors: AppExecutors) {

    // LiveData storing the latest downloaded weather forecasts
    private lateinit var mContext: Context
    private lateinit var mDownloadedWeatherForecasts: MutableLiveData<Array<WeatherTable?>>
    private lateinit var mExecutors: AppExecutors

    companion object {
        // The number of days we want our API to return, set to 14 days or two weeks
        val NUM_DAYS = 14
        private val LOG_TAG = WeatherNetworkDataSource::class.java.simpleName
        // Interval at which to sync with the weather. Use TimeUnit for convenience, rather than
        // writing out a bunch of multiplication ourselves and risk making a silly mistake.
        private val SYNC_INTERVAL_HOURS = 3
        private val SYNC_INTERVAL_SECONDS = TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_HOURS.toLong()).toInt()
        private val SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 3

        // For Singleton instantiation
        @Volatile
        private var instance: WeatherNetworkDataSource? = null

        /**
         * Get the singleton for this class
         */
        fun getInstance(context: Context, executors: AppExecutors): WeatherNetworkDataSource {
            return instance ?: synchronized(this) {
                instance ?: WeatherNetworkDataSource(context, executors).also { instance = it }
            }
        }
    }

    // For Singleton instantiation
    private val LOCK = Any()
    private var sInstance: WeatherNetworkDataSource? = null

    private val SUNSHINE_SYNC_TAG = "weather-sync"

    init {
        mContext = context
        mExecutors = executors
        mDownloadedWeatherForecasts = MutableLiveData<Array<WeatherTable?>>()
    }

    fun getCurrentWeatherForecasts(): LiveData<Array<WeatherTable?>> {
        return mDownloadedWeatherForecasts
    }

    /**
     * Starts an intent service to fetch the weather.
     */
    fun startFetchWeatherService() {
        val intentToFetch = Intent(mContext, WeatherSyncIntentService::class.java)
        mContext.startService(intentToFetch)
        Log.d(LOG_TAG, "Service created")
    }

    /**
     * Schedules a repeating job service which fetches the weather.
     */
    fun scheduleRecurringFetchWeatherSync() {


        val driver = GooglePlayDriver(mContext)
        val dispatcher = FirebaseJobDispatcher(driver)

        // Create the Job to periodically sync Sunshine
        val syncSunshineJob = dispatcher.newJobBuilder()
                /* The Service that will be used to sync Weather's data */
                .setService(WeatherFirebaseJobService::class.java)
                /* Set the UNIQUE tag used to identify this Job */
                .setTag(SUNSHINE_SYNC_TAG)
                /*
                 * Network constraints on which this Job should run. We choose to run on any
                 * network, but you can also choose to run only on un-metered networks or when the
                 * device is charging. It might be a good idea to include a preference for this,
                 * as some users may not want to download any data on their mobile plan. ($$$)
                 */
                .setConstraints(Constraint.ON_ANY_NETWORK)
                /*
                 * setLifetime sets how long this job should persist. The options are to keep the
                 * Job "forever" or to have it die the next time the device boots up.
                 */
                .setLifetime(Lifetime.FOREVER)
                /*
                 * We want Sunshine's weather data to stay up to date, so we tell this Job to recur.
                 */
                .setRecurring(true)
                /*
                 * We want the weather data to be synced every 3 to 4 hours. The first argument for
                 * Trigger's static executionWindow method is the start of the time frame when the
                 * sync should be performed. The second argument is the latest point in time at
                 * which the data should be synced. Please note that this end time is not
                 * guaranteed, but is more of a guideline for FirebaseJobDispatcher to go off of.
                 */
                .setTrigger(Trigger.executionWindow(
                        SYNC_INTERVAL_SECONDS,
                        SYNC_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                /*
                 * If a Job with the tag with provided already exists, this new job will replace
                 * the old one.
                 */
                .setReplaceCurrent(true)
                /* Once the Job is ready, call the builder's build method to return the Job */
                .build()

        // Schedule the Job with the dispatcher
        dispatcher.schedule(syncSunshineJob)
        Log.d(LOG_TAG, "Job scheduled")
    }

    /**
     * Gets the newest weather
     */
    internal fun fetchWeather() {
        Log.d(LOG_TAG, "Fetch weather started")
        fetchWeatherusingRetrofitCoRoutine()
    }

    /*
       Traiditional way for getting data from server
     */
    internal fun fetchWeatherWithoutRetrofit() {
        Log.d(LOG_TAG, "Fetch weather ")
        mExecutors.networkIO().execute {
            try {

                // The getUrl method will return the URL that we need to get the forecast JSON for the
                // weather. It will decide whether to create a URL based off of the latitude and
                // longitude or off of a simple location as a String.

                val weatherRequestUrl = NetworkUtils.getUrl()

                // Use the URL to retrieve the JSON
                val jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl!!)

                // Parse the JSON into a list of weather forecasts
                val response = OpenWeatherJsonParser().parse(jsonWeatherResponse)
                Log.d(LOG_TAG, "JSON Parsing finished")


                // As long as there are weather forecasts, update the LiveData storing the most recent
                // weather forecasts. This will trigger observers of that LiveData, such as the
                // SunshineRepository.
                if (response != null && response!!.getWeatherForecast().size != 0) {
                    Log.d(LOG_TAG, "JSON not null and has " + response!!.getWeatherForecast().size
                            + " values")
                    Log.d(LOG_TAG, String.format("First value is %1.0f and %1.0f",
                            response!!.getWeatherForecast()[0]!!.min,
                            response!!.getWeatherForecast()[0]!!.max))

                    // When you are off of the main thread and want to update LiveData, use postValue.
                    // It posts the update to the main thread.
                    mDownloadedWeatherForecasts.postValue(response!!.getWeatherForecast())

                    // If the code reaches this point, we have successfully performed our sync
                }
            } catch (e: Exception) {
                // Server probably invalid
                e.printStackTrace()
            }
        }
    }

    /*
      Using Retrofit for getting data from server
    */
    internal fun fetchWeatherusingRetrofit() {
        Log.d(LOG_TAG, "Fetch weather using Retrofit")
        var weatherinfo = WeatherRetrofitClient.getInstance().getWeatherInfo(locationQuery, format, units, Integer.toString(WeatherNetworkDataSource.NUM_DAYS))
        weatherinfo.enqueue(object : Callback<WeatherData?> {
            override fun onResponse(call: Call<WeatherData?>, response: Response<WeatherData?>) {
                if (response.isSuccessful()) {
                    Log.d(LOG_TAG, "Fetch weather using Retrofit Success")
                    mDownloadedWeatherForecasts.postValue(response!!.body()!!.toWeatherTableData())
                } else {
                    val statusCode = response.code()
                    Log.d(LOG_TAG, "Fetch weather using Retrofit error "+statusCode)
                    // handle request errors depending on status code
                }
            }

            override fun onFailure(call: Call<WeatherData?>, t: Throwable) {
                Log.i(LOG_TAG,"Retrofit Error")
            }
        })

    }

    /*
     Using Rxjava in Retrofit for getting data from server
   */
    internal fun fetchWeatherusingRetrofitRxJava() {

        WeatherRxJavaRetrofitClient.getInstance().getWeatherInfo(locationQuery, format, units, Integer.toString(WeatherNetworkDataSource.NUM_DAYS))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.d(LOG_TAG, "Fetch weather using Rx Retrofit Success")
                            mDownloadedWeatherForecasts.postValue(result!!.toWeatherTableData())
                        },
                        { error -> Log.i(LOG_TAG, "Rx java Error") }
                )

    }

    /*
    Using Rxjava in Retrofit for getting data from server
  */
    internal fun fetchWeatherusingRetrofitCoRoutine() {

//        GlobalScope.launch(Dispatchers.Main) {
//            val request =  WeatherCoroutineJavaRetrofitClient.getInstance().getWeatherInfo(locationQuery, format, units, Integer.toString(WeatherNetworkDataSource.NUM_DAYS))
//            try {
//                val response = request.await()
//                // Do something with the response
//                Log.d(LOG_TAG, "Fetch weather using Coroutinue Retrofit Success")
//                mDownloadedWeatherForecasts.postValue(response!!.toWeatherTableData())
//            } catch (e: HttpException) {
//                Log.i(LOG_TAG, "Coroutine Http  Error")
//            } catch (e: Throwable) {
//                Log.i(LOG_TAG, "Coroutine Error")
//            }
//        }

        CoroutineScope(Dispatchers.IO).launch {
            val request =  WeatherCoroutineJavaRetrofitClient.getInstance().getWeatherInfo(locationQuery, format, units, Integer.toString(WeatherNetworkDataSource.NUM_DAYS))
             withContext(Dispatchers.IO) {
                 try {
                     val response = request.await()
                     // Do something with the response
                     Log.d(LOG_TAG, "Fetch weather using Coroutinue Retrofit Success")
                     mDownloadedWeatherForecasts.postValue(response!!.toWeatherTableData())
                 } catch (e: HttpException) {
                     Log.i(LOG_TAG, "Coroutine Http  Error")
                 } catch (e: Throwable) {
                     Log.i(LOG_TAG, "Coroutine Error")
                 }
             }
        }
    }


}