package com.ar.mylapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyLApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Lo que va aca es codigo que necesitemos
        // que se ejecute al iniciar la app
    }
}