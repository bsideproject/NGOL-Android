package com.nuguollim.data.repository.auth

import com.nuguollim.data.model.auth.AuthInfo
import com.nuguollim.data.model.auth.SignUpData
import com.nuguollim.data.model.auth.TokenData
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface AuthRepository {

    fun createToken(body: RequestBody): Flow<TokenData>

    fun signup(body: RequestBody): Flow<SignUpData>

    fun getAuthInfo(): Flow<AuthInfo>

    suspend fun setAuthInfo(provideType: String, provideId: String)

    fun getProvideToken(): Flow<String?>

    suspend fun setProvideToken(token: String)

}