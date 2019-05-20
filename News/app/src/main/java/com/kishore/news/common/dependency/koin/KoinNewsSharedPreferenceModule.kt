package com.kishore.news.common.dependency.koin

import androidx.preference.PreferenceManager
import com.kishore.news.common.depndency.NewsSharedPreferenceKoin
import org.koin.dsl.module.module


val mySharedPreferenceModule = module {

    // Inject constructor in a module with injection by get()
    single { NewsSharedPreferenceKoin(PreferenceManager.getDefaultSharedPreferences(get())) }
}

