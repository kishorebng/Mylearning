package com.kishore.apparchitecture.model.network.retrofit.rxjava

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kishore.apparchitecture.model.network.retrofit.WEATHER_URL
import com.kishore.apparchitecture.model.network.retrofit.responsepojo.WeatherData
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WeatherCoroutineJavaRetrofitClient {

    private val weatherApi: WeatherCoroutineApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        weatherApi = retrofit.create(WeatherCoroutineApi::class.java)
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: WeatherCoroutineJavaRetrofitClient? = null

        fun getInstance(): WeatherCoroutineJavaRetrofitClient {
            return instance ?: synchronized(this) {
                instance ?: WeatherCoroutineJavaRetrofitClient().also { instance = it }
            }
        }
    }

    fun getWeatherInfo(locationQuery: String, outputformat: String, unit: String, days: String): Deferred<WeatherData?> {
        return weatherApi.getWeatherInfo(locationQuery, outputformat, unit, days)
    }
}