package com.kishore.apparchitecture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kishore.apparchitecture.model.WeatherRepository
import com.kishore.apparchitecture.model.database.WeatherTable
import java.util.*

class ForecastDetailViewModel (weatherRepository: WeatherRepository, date : Date): ViewModel() {

    val weatherData: LiveData<WeatherTable>

    init {
        weatherData = weatherRepository.getWeatherByDate(date)
    }

}