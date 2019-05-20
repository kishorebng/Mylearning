package com.kishore.news.common.depndency.dagger

import android.content.Context
import androidx.preference.PreferenceManager
import com.kishore.news.common.depndency.NewsSharedPreferenceDagger
import dagger.Module
import dagger.Provides

/*
  Module means the class which contains methods who will provide dependencies.
 */
@Module
class NewsSharedPreferencesModule(var context: Context) {

    @Provides
    fun provideSharedPreferences(): NewsSharedPreferenceDagger {
        return NewsSharedPreferenceDagger(PreferenceManager.getDefaultSharedPreferences(context))
    }
}