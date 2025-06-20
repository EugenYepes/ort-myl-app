package com.ar.mylapp.data.dataStore

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserDataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore

    val token: Flow<String?> = dataStore.data.map { it[UserDataStoreKeys.TOKEN] }

    suspend fun saveToken(value: String?) {
        dataStore.edit { prefs ->
            if (value != null) prefs[UserDataStoreKeys.TOKEN] = value
            else prefs.remove(UserDataStoreKeys.TOKEN)
            Log.d("saveToken USDM", "token: $value")
        }
    }

    suspend fun getToken(): String? {
        val preferences = dataStore.data.first()
        return preferences[UserDataStoreKeys.TOKEN]
    }

    /*suspend fun clearAll() {
        dataStore.edit { prefs -> prefs.remove(UserDataStoreKeys.TOKEN) }
    }*/
}