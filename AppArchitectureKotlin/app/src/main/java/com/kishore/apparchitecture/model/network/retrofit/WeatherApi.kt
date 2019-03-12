package com.kishore.apparchitecture.model.network.retrofit

import com.kishore.apparchitecture.model.network.retrofit.responsepojo.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    //https://andfun-weather.udacity.com/weather?q=Newark%2C%20NJ&mode=json&units=metric&cnt=14

    @GET("/weather")
    fun getWeatherInfo(@Query(QUERY_PARAM) locationQuery: String,
                       @Query(FORMAT_PARAM) outputformat: String,
                       @Query(UNITS_PARAM) unit: String,
                       @Query(DAYS_PARAM) days: String):  Call<WeatherData?>

}