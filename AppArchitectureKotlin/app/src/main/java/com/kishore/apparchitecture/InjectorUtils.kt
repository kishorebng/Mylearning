package com.kishore.apparchitecture

import android.content.Context
import com.kishore.apparchitecture.model.WeatherRepository
import com.kishore.apparchitecture.model.database.WeatherDataBase
import com.kishore.apparchitecture.model.network.WeatherNetworkDataSource
import com.kishore.apparchitecture.viewmodel.ForecastDetailViewModelFactory
import com.kishore.apparchitecture.viewmodel.ForecastListViewModelFactory
import java.util.*


class InjectorUtils {
    companion object {
        fun provideRepository(context: Context): WeatherRepository {
            val database = WeatherDataBase.getInstance(context.getApplicationContext())
            val executors = AppExecutors.getInstance()
            val networkDataSource = WeatherNetworkDataSource.getInstance(context.getApplicationContext(), executors)
            return WeatherRepository.getInstance(database.weatherDao(),networkDataSource,executors)
        }
//
        fun provideNetworkDataSource(context: Context): WeatherNetworkDataSource {
            // This call to provide repository is necessary if the app starts from a service - in this
            // case the repository will not exist unless it is specifically created.
            provideRepository(context.getApplicationContext())
            val executors = AppExecutors.getInstance()
            return WeatherNetworkDataSource.getInstance(context.getApplicationContext(), executors)
        }

        fun provideForecastDetailViewModelFactory(context: Context, date: Date): ForecastDetailViewModelFactory {
            val repository = provideRepository(context.getApplicationContext())
            return ForecastDetailViewModelFactory(repository, date)
        }

        fun provideForecastListViewModelFactory(context: Context): ForecastListViewModelFactory {
            val repository = provideRepository(context.getApplicationContext())
            return ForecastListViewModelFactory(repository)
        }
    }
}