package com.ar.mylapp

import android.app.Application
import com.ar.mylapp.network.Config
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MylApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Config.baseUrl
        //Config.baseUrl = resources.getString(R.string.myl_api_base_url)

    }
}
