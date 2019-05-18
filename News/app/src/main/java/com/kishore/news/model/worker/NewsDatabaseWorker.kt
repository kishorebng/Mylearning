package com.kishore.news.model.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.kishore.news.model.database.NewsDataBase
import com.kishore.news.model.network.newsapiresponse.NewsData
import kotlinx.coroutines.coroutineScope

class NewsDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val TAG by lazy { NewsDatabaseWorker::class.java.simpleName }
    val NEWS_JSON_FILENAME = "news.json"

    override suspend fun doWork(): Result = coroutineScope {

        try {
            applicationContext.assets.open(NEWS_JSON_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val data =   object : TypeToken<NewsData>() {}.type
                    val news: NewsData = Gson().fromJson(jsonReader, data)

                    val database = NewsDataBase.getInstance(applicationContext)
//                    database.newsDao().bulkInsert(*newsDetail.toNewsTableData())
                    database.newsDao().insertAll(news.toNewsTableDataList(1))
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error News database", ex)
            Result.failure()
        }
    }
}