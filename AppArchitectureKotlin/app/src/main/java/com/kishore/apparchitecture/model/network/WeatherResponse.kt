package com.kishore.apparchitecture.model.network

import com.kishore.apparchitecture.model.database.WeatherTable

class WeatherResponse(weatherForecast: Array<WeatherTable?>) {

    private lateinit var mWeatherForecast: Array<WeatherTable?>

    init {
        mWeatherForecast = weatherForecast
    }

    fun getWeatherForecast(): Array<WeatherTable?> {
        return mWeatherForecast
    }

}