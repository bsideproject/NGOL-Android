package com.nugu.data_store.data_source.splash

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.nugu.data_store.keys.SPLASH_FIRST_INIT
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SplashLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SplashLocalDataSource {

    override suspend fun getFirstInit(): Boolean =
        dataStore.data.map { it[SPLASH_FIRST_INIT] }.first() ?: true

    override suspend fun setFirstInit(isInit: Boolean) {
        dataStore.edit { it[SPLASH_FIRST_INIT] = isInit }
    }

}