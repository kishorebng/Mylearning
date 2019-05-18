package com.kishore.news.common

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/*
  Module means the class which contains methods who will provide dependencies.
 */
@Module
class NewsSharedPreferencesModule(var context: Context) {

    @Provides
    fun provideSharedPreferences(): NewsSharedPreference {
        return NewsSharedPreference(PreferenceManager.getDefaultSharedPreferences(context))
    }
}