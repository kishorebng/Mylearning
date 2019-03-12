package com.kishore.apparchitecture.model.network.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.kishore.apparchitecture.InjectorUtils

class WeatherSyncIntentService : IntentService("WeatherSyncIntentService") {

    private val LOG_TAG = WeatherSyncIntentService::class.java.simpleName
    override fun onHandleIntent(intent: Intent?) {
        Log.d(LOG_TAG, "Intent service started")
        val networkDataSource = InjectorUtils.provideNetworkDataSource(this.applicationContext)
        networkDataSource.fetchWeather()
    }
}