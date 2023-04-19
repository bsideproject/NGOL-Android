package com.nugu.data_store.data_source.auth

import com.nugu.data_store.model.LocalAuthInfo
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {

    suspend fun setAuthInfo(provideType: String, provideId: String)

    fun getAuthInfo(): Flow<LocalAuthInfo>

}