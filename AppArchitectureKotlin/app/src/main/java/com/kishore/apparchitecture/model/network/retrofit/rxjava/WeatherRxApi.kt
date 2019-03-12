package com.kishore.apparchitecture.model.network.retrofit.rxjava

import com.kishore.apparchitecture.model.network.retrofit.DAYS_PARAM
import com.kishore.apparchitecture.model.network.retrofit.FORMAT_PARAM
import com.kishore.apparchitecture.model.network.retrofit.QUERY_PARAM
import com.kishore.apparchitecture.model.network.retrofit.UNITS_PARAM
import com.kishore.apparchitecture.model.network.retrofit.responsepojo.WeatherData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRxApi {

    //https://andfun-weather.udacity.com/weather?q=Newark%2C%20NJ&mode=json&units=metric&cnt=14

    @GET("/weather")
    fun getWeatherInfo(@Query(QUERY_PARAM) locationQuery: String,
                       @Query(FORMAT_PARAM) outputformat: String,
                       @Query(UNITS_PARAM) unit: String,
                       @Query(DAYS_PARAM) days: String): Observable<WeatherData?>

}