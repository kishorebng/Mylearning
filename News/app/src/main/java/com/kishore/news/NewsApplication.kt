package com.kishore.news

import android.app.Application
import android.util.Log
import com.kishore.news.common.DaggerNewsComponent
//import com.kishore.news.common.DaggerNewsComponent
import com.kishore.news.common.NewsComponent
import com.kishore.news.common.NewsSharedPreference
import com.kishore.news.common.NewsSharedPreferencesModule
import javax.inject.Inject

class NewsApplication : Application() {

    lateinit var newsComponent: NewsComponent

    override fun onCreate() {
        super.onCreate()
        newsComponent = DaggerNewsComponent
                .builder()
                .newsSharedPreferencesModule(NewsSharedPreferencesModule(this.applicationContext)).build()
    }
}