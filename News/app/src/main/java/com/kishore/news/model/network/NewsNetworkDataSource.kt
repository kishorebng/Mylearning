package com.kishore.news.model.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.kishore.news.NewsApplication
import com.kishore.news.common.NewsUtil
import com.kishore.news.common.depndency.NewsSharedPreferenceDagger
import com.kishore.news.model.database.NewsTable
import com.kishore.news.model.network.newsapi.NewsRetrofitClient
import com.kishore.news.model.worker.NewsCoroutineWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.URLEncoder
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NewsNetworkDataSource(context: Context,
                            var  mHeadlines :MutableLiveData<List<NewsTable?>> = MutableLiveData<List<NewsTable?>>(),
                            var  mAllNews :MutableLiveData<List<NewsTable?>> = MutableLiveData<List<NewsTable?>>()) {

    private val LOG_TAG = NewsNetworkDataSource::class.java.simpleName
    private lateinit var mContext: Context
    @Inject
    lateinit var mySharedPreferencesDagger: NewsSharedPreferenceDagger

    init {
        mContext = context
        var application : NewsApplication = mContext.applicationContext as NewsApplication
        application.newsComponent.injectHere(this)
    }


    companion object {
        // The number of days we want our API to return, set to 14 days or two weeks

        // For Singleton instantiation
        @Volatile
        private var instance: NewsNetworkDataSource? = null

        /**
         * Get the singleton for this class
         */
        fun getInstance(context: Context): NewsNetworkDataSource {
            return instance ?: synchronized(this) {
                instance ?: NewsNetworkDataSource(context).also { instance = it }
            }
        }
    }

    fun getHeadlines(): LiveData<List<NewsTable?>> {
        return mHeadlines
    }


    fun getAllNews(): LiveData<List<NewsTable?>> {
        return mAllNews
    }

    /*
      Using co routine in Retrofit for getting newsdata from server
   */
    fun getHeadlinesFromNetwork() {

        CoroutineScope(Dispatchers.IO).launch {
            val request =  NewsRetrofitClient.getInstance().getHeadlines(setHeadlinesParameters());
            withContext(Dispatchers.IO) {
                try {
                    val response = request.await()
                    // Do something with the response
                    Log.d(LOG_TAG, "Fetch weather using Coroutinue Retrofit Success")
                    mHeadlines.postValue(response!!.toNewsTableDataList(1))
                } catch (e: HttpException) {
                    Log.i(LOG_TAG, "Coroutine Http  Error"+e.message() + " "+e.code() +e.response().raw().request().url())
                } catch (e: Throwable) {
                    Log.i(LOG_TAG, "Coroutine Error")
                }
            }
        }
    }

    /*
      Using co routine in Retrofit for getting newsdata from server
   */
    fun getAllNewsFromNetwork() {
        CoroutineScope(Dispatchers.IO).launch {
            val request =  NewsRetrofitClient.getInstance().getAllNews(setAllNewsParameters())
            withContext(Dispatchers.IO) {
                try {
                    val fullresponse = request.await()
                    val response = request.await()
                    // Do something with the response
                    Log.d(LOG_TAG, "Fetch weather using Coroutinue Retrofit Success")
                    mAllNews.postValue(response!!.toNewsTableDataList(0))
                } catch (e: HttpException) {
                    Log.i(LOG_TAG, "Coroutine Http  Error"+e.message() + " "+e.code() +e.response().raw().request().url())
                } catch (e: Throwable) {
                    Log.i(LOG_TAG, "Coroutine Error")
                }
            }
        }
    }

     fun setHeadlinesParameters() : Map<String, String> {
         val countryCode = mySharedPreferencesDagger.getStringPreference(mContext.getString(com.kishore.news.R.string.country_key),NewsUtil.getCountryCode())
         val data: MutableMap<String, String> = mutableMapOf<String, String>()
         data.put("country", countryCode)
         return data

     }

    fun setAllNewsParameters() : Map<String, String> {
        val query = mySharedPreferencesDagger.getStringPreference(mContext.getString(com.kishore.news.R.string.country_entry_key), NewsUtil.getDisplayCountry())
        val data: MutableMap<String, String> = mutableMapOf<String, String>()
        data.put("q", URLEncoder.encode(query, "UTF-8"))
        data.put("from", NewsUtil.getformatedToday())
        data.put("to", NewsUtil.getformatedToday())
        data.put("sortBy", "publishedAt")
        data.put("pageSize", "40")
        return data
    }


    fun scheduleRecurringNewsSync() {
        val constraints: Constraints = Constraints.Builder().apply {
            setRequiredNetworkType(NetworkType.CONNECTED)
        }.build()

        val request: PeriodicWorkRequest = PeriodicWorkRequestBuilder<NewsCoroutineWorker>(PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
                                           TimeUnit.MINUTES, PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()
        WorkManager.getInstance(mContext).enqueue(request)
    }


    fun fetchNews() {
        val request = OneTimeWorkRequestBuilder<NewsCoroutineWorker>().build()
        WorkManager.getInstance(mContext).enqueue(request)
    }

}