package com.kishore.news.model.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.google.common.util.concurrent.ListenableFuture
import com.kishore.news.InjectorUtils

//class NewsListenableWorker(appContext: Context, params: WorkerParameters) :
//        ListenableWorker(appContext, params) {
//
//    override fun startWork(): ListenableFuture<Result> {
//        // Do your work here.
//        val networkDataSource = InjectorUtils.provideNetworkDataSource(this.applicationContext)
//        networkDataSource.getHeadlinesFromNetwork()
//        networkDataSource.getHeadlines()
//        return Result.success()
//    }
//
//    override fun onStopped() {
//        // Cleanup because you are being stopped.
//    }
//}
