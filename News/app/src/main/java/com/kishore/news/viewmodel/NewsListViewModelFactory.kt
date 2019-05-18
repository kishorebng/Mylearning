package com.kishore.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kishore.news.model.NewsRepository

/**
 *  this class is needed as Viewmodel constructor is not empty it takes parameter
 *  provider factory  knows how to create view models.
 * This one could then even use dependency injection to get everything you need.
 * This way you move the responsibility of creating view models
 * into a separate class which already made your code better (single responsibility principle).
 */
class NewsListViewModelFactory (
        private val repository: NewsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = NewsListViewModel(repository) as T
}
