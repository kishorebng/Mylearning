package com.kishore.news

import android.content.Context
import com.kishore.news.model.NewsRepository
import com.kishore.news.model.database.NewsDataBase
import com.kishore.news.model.network.NewsNetworkDataSource
import com.kishore.news.viewmodel.NewsDetailsViewModel
import com.kishore.news.viewmodel.NewsListViewModel
import com.kishore.news.viewmodel.NewsListViewModelFactory

object InjectorUtils {

    fun getNewsRepository(context: Context): NewsRepository {
        return NewsRepository.getInstance(
                provideDataBase(context), provideNetworkDataSource(context))
    }

    fun provideNetworkDataSource(context: Context): NewsNetworkDataSource {
        return NewsNetworkDataSource.getInstance(context.getApplicationContext())
    }

    fun provideDataBase(context: Context): NewsDataBase {
       return NewsDataBase.getInstance(context.getApplicationContext())
    }


    fun provideNewsListViewModelFactory(context: Context): NewsListViewModelFactory {
        val repository = getNewsRepository(context)
        return NewsListViewModelFactory(repository)
    }

    fun provideNewsDetailsModel(context: Context, id :Int): NewsDetailsViewModel {
        val repository = getNewsRepository(context)
        return NewsDetailsViewModel(repository,id)
    }

}