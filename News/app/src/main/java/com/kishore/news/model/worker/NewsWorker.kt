package com.kishore.news.model.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters

class NewsWorker(appContext: Context, params: WorkerParameters) :
        Worker(appContext, params) {
    override fun doWork(): Result {
        return Result.success()
    }

}