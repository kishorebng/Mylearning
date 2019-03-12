package com.kishore.apparchitecture.model.network.retrofit

import com.kishore.apparchitecture.model.network.retrofit.responsepojo.WeatherData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WeatherRetrofitClient {

    private val weatherApi: WeatherApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        weatherApi = retrofit.create(WeatherApi::class.java)
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: WeatherRetrofitClient? = null

        fun getInstance(): WeatherRetrofitClient {
            return instance ?: synchronized(this) {
                instance ?: WeatherRetrofitClient().also { instance = it }
            }
        }
    }

    fun getWeatherInfo(locationQuery: String, outputformat: String, unit: String, days: String): Call<WeatherData?> {
        return weatherApi.getWeatherInfo(locationQuery, outputformat, unit, days)
    }
}