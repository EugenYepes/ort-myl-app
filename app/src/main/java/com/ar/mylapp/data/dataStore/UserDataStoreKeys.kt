package com.ar.mylapp.data.dataStore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "user_prefs")

object UserDataStoreKeys {
    /*val EMAIL = stringPreferencesKey("email")
    val STORE_NAME = stringPreferencesKey("store_name")
    val ADDRESS = stringPreferencesKey("address")
    val PHONE = stringPreferencesKey("phone")*/
    val TOKEN = stringPreferencesKey("token")
    //val CURRENT_USER = stringPreferencesKey("currentUser")
}