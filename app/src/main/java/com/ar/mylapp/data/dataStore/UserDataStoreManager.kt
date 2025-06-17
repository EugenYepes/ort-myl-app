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

    /*val email: Flow<String?> = dataStore.data.map { it[UserDataStoreKeys.EMAIL] }
    val storeName: Flow<String?> = dataStore.data.map { it[UserDataStoreKeys.STORE_NAME] }
    val address: Flow<String?> = dataStore.data.map { it[UserDataStoreKeys.ADDRESS] }
    val phone: Flow<String?> = dataStore.data.map { it[UserDataStoreKeys.PHONE] }*/
    val token: Flow<String?> = dataStore.data.map { it[UserDataStoreKeys.TOKEN] }
    //val currentUser: Flow<String?> = dataStore.data.map { it[UserDataStoreKeys.CURRENT_USER] }

    /*suspend fun saveEmail(value: String) {
        dataStore.edit { it[UserDataStoreKeys.EMAIL] = value }
    }

    suspend fusaveStoreName(value: String) {
        dataStore.edit { it[UserDataStoreKeys.STORE_NAME] = value }
    }

    suspend fun saveAddress(value: String) {
        dataStore.edit { it[UserDataStoreKeys.ADDRESS] = value }
    }

    suspend fun savePhone(value: String) {
        dataStore.edit { it[UserDataStoreKeys.PHONE] = value }
    }*/

    suspend fun saveToken(value: String?) {
        dataStore.edit { prefs ->
            if (value != null) prefs[UserDataStoreKeys.TOKEN] = value
            else prefs.remove(UserDataStoreKeys.TOKEN)
            Log.d("saveToken USDM", "token: $value")
        }
    }

    /*suspend fun saveCurrentUser(value: String?) {
        dataStore.edit { prefs ->
            if (value != null) prefs[UserDataStoreKeys.CURRENT_USER] = value
            else prefs.remove(UserDataStoreKeys.CURRENT_USER)
        }
    }*/

    suspend fun getToken(): String? {
        val preferences = dataStore.data.first()
        return preferences[UserDataStoreKeys.TOKEN]
    }

    /*suspend fun getEmail(): String? {
        val preferences = dataStore.data.first()
        return preferences[UserDataStoreKeys.EMAIL]
    }*/


    suspend fun clearAll() {
        /*dataStore.edit { it[UserDataStoreKeys.EMAIL] = "" }
        dataStore.edit { it[UserDataStoreKeys.STORE_NAME] = "" }
        dataStore.edit { it[UserDataStoreKeys.ADDRESS] = "" }
        dataStore.edit { it[UserDataStoreKeys.PHONE] = "" }*/
        dataStore.edit { prefs -> prefs.remove(UserDataStoreKeys.TOKEN) }
//dataStore.edit { prefs -> prefs.remove(UserDataStoreKeys.CURRENT_USER) }
    }
}