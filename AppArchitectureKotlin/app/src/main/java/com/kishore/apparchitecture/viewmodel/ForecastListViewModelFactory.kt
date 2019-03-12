package com.kishore.apparchitecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kishore.apparchitecture.model.WeatherRepository

/**
 *  this class is needed as Viewmodel constructor is not empty it takes parameter
 *  provider factory  knows how to create view models.
 * This one could then even use dependency injection to get everything you need.
 * This way you move the responsibility of creating view models into a separate class which already made your code better (single responsibility principle).
 */
class ForecastListViewModelFactory (
        private val repository: WeatherRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = ForecastListViewModel(repository) as T
}
