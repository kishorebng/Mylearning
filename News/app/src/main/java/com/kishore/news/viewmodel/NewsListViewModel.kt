package com.kishore.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kishore.news.model.NewsRepository
import com.kishore.news.model.database.NewsTable


class NewsListViewModel(val newsRepository: NewsRepository) : ViewModel() {

      lateinit var newsdata: LiveData<List<NewsTable>>
      var headlinesnewsdata: LiveData<List<NewsTable>> = newsRepository.getHeadlines()

      private val query = MutableLiveData<String>()
      init {
          newsdata =  Transformations.switchMap(
                  query,
                  ::allNews
          )
          query.value = "%%"
      }

      fun searchNews(queryString: String) = apply { query.value = queryString  }

     fun allNews(query: String) =  newsRepository.getSearchResult(query)

}