package com.plass.travlingadmin.local

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.plass.travlingadmin.TravelingApplication

object SharedPreferencesManager {
    private var instance: SharedPreferences? = null

    private fun getInstance(): SharedPreferences {
        if (instance == null) {
            instance = TravelingApplication.context?.getSharedPreferences("traveling", MODE_PRIVATE)
        }
        return instance!!
    }

    fun set(key: String, value: String) {
        val editor = getInstance().edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun get(key: String): String? {
        return getInstance().getString(key, "")
    }

}