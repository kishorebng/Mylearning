package com.kishore.news

import android.app.Application
import com.kishore.news.common.dependency.koin.mySharedPreferenceModule
import com.kishore.news.common.depndency.dagger.DaggerNewsComponent
import com.kishore.news.common.depndency.dagger.NewsComponent
import com.kishore.news.common.depndency.dagger.NewsSharedPreferencesModule
import org.koin.android.ext.android.startKoin


class NewsApplication : Application() {

    lateinit var newsComponent: NewsComponent

    override fun onCreate() {
        super.onCreate()
        // Dagger
        newsComponent = DaggerNewsComponent
                .builder()
                .newsSharedPreferencesModule(NewsSharedPreferencesModule(this.applicationContext)).build()

        //Koin
        startKoin(this, listOf(mySharedPreferenceModule))


    }
}