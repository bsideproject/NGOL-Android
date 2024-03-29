package com.nugu.data_store.data_source.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.nugu.data_store.keys.AUTH_PROVIDE_ID
import com.nugu.data_store.keys.AUTH_PROVIDE_TOKEN
import com.nugu.data_store.keys.AUTH_PROVIDE_TYPE
import com.nugu.data_store.model.LocalAuthInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AuthLocalDataSource {

    override fun getAuthInfo(): Flow<LocalAuthInfo> = dataStore.data.map { preference ->
        val provideType = preference[AUTH_PROVIDE_TYPE]
        val provideId = preference[AUTH_PROVIDE_ID]

        LocalAuthInfo(
            provideType = provideType!!,
            provideId = provideId!!,
        )
    }

    override suspend fun setAuthInfo(provideType: String, provideId: String, ) {
        dataStore.edit { settings ->
            settings[AUTH_PROVIDE_TYPE] = provideType
            settings[AUTH_PROVIDE_ID] = provideId
        }
    }

    override suspend fun clearAuthInfo() {
        dataStore.edit { settings ->
            settings.remove(AUTH_PROVIDE_TYPE)
            settings.remove(AUTH_PROVIDE_ID)
            settings.remove(AUTH_PROVIDE_TOKEN)
        }
    }

    override fun getProvideToken(): Flow<String?> = dataStore.data.map { it[AUTH_PROVIDE_TOKEN] }

    override suspend fun setProvideToken(token: String) {
        dataStore.edit { it[AUTH_PROVIDE_TOKEN] = token }
    }

}