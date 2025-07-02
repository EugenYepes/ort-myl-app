package com.ar.mylapp.data.dataStore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "user_prefs")

object UserDataStoreKeys {
    val TOKEN = stringPreferencesKey("token")
}