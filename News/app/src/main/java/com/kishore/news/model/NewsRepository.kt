package com.kishore.news.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.kishore.news.model.database.*
import com.kishore.news.model.network.NewsNetworkDataSource
import com.kishore.news.model.worker.NewsDatabaseWorker

class NewsRepository(newsDataBase: NewsDataBase,
                     newsNetworkDataSource: NewsNetworkDataSource) {

    private val LOG_TAG = NewsRepository::class.java.getSimpleName()
    private lateinit var mNewsDao: NewsDao
    private lateinit var mNewsDataBase: NewsDataBase
    private lateinit var mNewsNetworkDataSource: NewsNetworkDataSource
    private var mInitialized = false

    companion object {
        // For Singleton instantiation
        private var instance: NewsRepository? = null

        fun getInstance(newsDataBase: NewsDataBase,
                        newsNetworkDataSource: NewsNetworkDataSource
        ) =
                instance ?: synchronized(this) {
                    instance
                            ?: NewsRepository(newsDataBase, newsNetworkDataSource).also { instance = it }
                }

    }

    init {
        mNewsDataBase = newsDataBase
        mNewsNetworkDataSource = newsNetworkDataSource
        // get dao objects
        mNewsDao = mNewsDataBase.newsDao()

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        val headlines = mNewsNetworkDataSource.getHeadlines()
        headlines.observeForever { breakingNewsFromNetwork ->
            mNewsDao.deleteNews(NewsModelUtil.HEADLINE)
            mNewsDao.insertAll(breakingNewsFromNetwork)
        }

        val allNews = mNewsNetworkDataSource.getAllNews()
        allNews.observeForever { allNewsFromNetwork ->
            mNewsDao.deleteNews(NewsModelUtil.NORMAL_NEWS)
            mNewsDao.insertAll(allNewsFromNetwork)
        }
    }

    @Synchronized
    private fun initializeData() {
        if (mInitialized) return
        mInitialized = true
        startRecurringService()
        fetchNews()
    }


    fun getHeadlines(): LiveData<List<NewsTable>> {
        initializeData()
        return mNewsDao.getheadlines()
    }

    fun getAllnews(): LiveData<List<NewsTable>>  {
//        val today = NewsModelUtil.getNormalizedUtcDateForToday()
        initializeData()
        return mNewsDao.getAllNews()
    }

    fun getNewsDetail(id: Int): NewsTable {
        return mNewsDao.getNewsDetail(id)
    }


    fun getSearchResult(query: String): LiveData<List<NewsTable>> {
        val searchResult: LiveData<List<NewsTable>> = mNewsDao.searchNews(query)
        return searchResult
    }


    fun prepopulatenews(context: Context) {
        val request = OneTimeWorkRequestBuilder<NewsDatabaseWorker>().build()
        WorkManager.getInstance(context).enqueue(request)
    }


    fun startRecurringService() {
        mNewsNetworkDataSource.scheduleRecurringNewsSync()
    }


    fun fetchNews() {
        mNewsNetworkDataSource.fetchNews()
    }

}