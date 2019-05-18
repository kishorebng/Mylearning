package com.kishore.news.view

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.util.Log
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.kishore.news.InjectorUtils
import com.kishore.news.R


class NewsSettingsFragment : PreferenceFragmentCompat() , OnSharedPreferenceChangeListener{


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.news_settings, rootKey)
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext()).edit()
        when (key) {
            getString(R.string.country_key) -> {
                val preference = findPreference(key) as ListPreference
                sharedPreferences.putString(getString(com.kishore.news.R.string.country_entry_key),preference.entry.toString())
                sharedPreferences.apply()
                InjectorUtils.getNewsRepository(requireActivity()).fetchNews()
            }
        }
    }

}