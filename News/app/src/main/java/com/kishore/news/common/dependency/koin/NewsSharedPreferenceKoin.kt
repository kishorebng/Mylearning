package com.kishore.news.common.depndency

import android.content.SharedPreferences

/*
  Provides means the methods who provide dependency.
  Inject means who accepts the object provided by the @Provides method.
 */

class NewsSharedPreferenceKoin (var sharedPreferences: SharedPreferences) {

    fun putStringPreference(key: String, data: String) {
        sharedPreferences.edit().putString(key, data).apply()
    }

    fun getStringPreference(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue)
    }
}