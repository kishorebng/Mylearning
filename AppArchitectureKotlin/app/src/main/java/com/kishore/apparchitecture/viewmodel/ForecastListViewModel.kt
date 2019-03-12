package com.kishore.apparchitecture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kishore.apparchitecture.model.WeatherRepository
import com.kishore.apparchitecture.model.forview.ListWeatherEntry


class ForecastListViewModel(weatherRepository: WeatherRepository) : ViewModel() {

    val mForecast: LiveData<List<ListWeatherEntry>>

    init {
        mForecast = weatherRepository.getCurrentWeatherForecasts()
    }

    fun getForecast() : LiveData<List<ListWeatherEntry>> {
        return mForecast
    }

}