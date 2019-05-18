package com.kishore.news.model.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kishore.news.InjectorUtils

class NewsCoroutineWorker(appContext: Context, params: WorkerParameters) :
        CoroutineWorker(appContext, params) {


    override suspend fun doWork(): Result {
        Log.i("Kishore","do work")
        val networkDataSource = InjectorUtils.provideNetworkDataSource(this.applicationContext)
        networkDataSource.getHeadlinesFromNetwork()
        networkDataSource.getAllNewsFromNetwork()
        return Result.success()
    }
}
