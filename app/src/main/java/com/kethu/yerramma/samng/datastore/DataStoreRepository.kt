package com.kethu.yerramma.samng.datastore

import androidx.datastore.preferences.core.Preferences

interface DataStoreRepository {
    suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): T
    suspend fun <T> putPreference(key: Preferences.Key<T>,value:T)
    suspend fun <T> removePreference(key: Preferences.Key<T>)
    suspend fun <T> clearAllPreference()
}