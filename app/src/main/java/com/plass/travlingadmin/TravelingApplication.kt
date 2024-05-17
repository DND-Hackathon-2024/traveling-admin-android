package com.plass.travlingadmin

import android.app.Application
import android.content.Context

class TravelingApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
    }

    companion object {
        var context: Context? = null
    }
}