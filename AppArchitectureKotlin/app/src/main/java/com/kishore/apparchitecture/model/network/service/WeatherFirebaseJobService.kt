package com.kishore.apparchitecture.model.network.service

import android.util.Log
import com.firebase.jobdispatcher.JobParameters
import com.firebase.jobdispatcher.JobService
import com.kishore.apparchitecture.InjectorUtils

class WeatherFirebaseJobService : JobService() {

    private val LOG_TAG = WeatherFirebaseJobService::class.java.simpleName
    override fun onStartJob(jobParameters: JobParameters): Boolean {
        Log.d(LOG_TAG, "Job service started")
        val networkDataSource = InjectorUtils.provideNetworkDataSource(this.applicationContext)
        networkDataSource.fetchWeather()
        jobFinished(jobParameters, false)
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }
}