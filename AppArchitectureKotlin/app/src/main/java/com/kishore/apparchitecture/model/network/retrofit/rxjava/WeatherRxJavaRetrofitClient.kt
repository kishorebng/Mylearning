package com.kishore.apparchitecture.model.network.retrofit.rxjava

import com.kishore.apparchitecture.model.network.retrofit.WEATHER_URL
import com.kishore.apparchitecture.model.network.retrofit.responsepojo.WeatherData
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class WeatherRxJavaRetrofitClient {

    private val weatherApi: WeatherRxApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        weatherApi = retrofit.create(WeatherRxApi::class.java)
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: WeatherRxJavaRetrofitClient? = null

        fun getInstance(): WeatherRxJavaRetrofitClient {
            return instance ?: synchronized(this) {
                instance ?: WeatherRxJavaRetrofitClient().also { instance = it }
            }
        }
    }

    fun getWeatherInfo(locationQuery: String, outputformat: String, unit: String, days: String): Observable<WeatherData?> {
        return weatherApi.getWeatherInfo(locationQuery, outputformat, unit, days)
    }
}