package com.kishore.news.common.depndency.dagger

import com.kishore.news.model.network.NewsNetworkDataSource
import com.kishore.news.view.NewsListFragment
import dagger.Component

@Component(modules = [NewsSharedPreferencesModule::class])
interface  NewsComponent{
//    fun injectHere(application: NewsApplication)
    fun injectHere(fragment : NewsListFragment)  // you need pass extact object injection is required
    fun injectHere(dataNetworkDataSource: NewsNetworkDataSource)
}