package com.kethu.yerramma.samng.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey


object DataStoreConstants {
    const val DATA_STORE_NAME = "my_data_store"
    val IS_USER_SIGNED_IN = booleanPreferencesKey("is_user_logged_in")
    val IS_NEW_USER = booleanPreferencesKey("is_new_user")
    val USER_NAME = stringPreferencesKey("user_name")
    val USER_EMAIL = stringPreferencesKey("user_email")
    val THEME = stringPreferencesKey("theme")
}