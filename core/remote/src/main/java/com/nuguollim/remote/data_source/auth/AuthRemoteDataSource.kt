package com.nuguollim.remote.data_source.auth

import com.nuguollim.remote.model.auth.SignupResponse
import com.nuguollim.remote.model.auth.TokenResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface AuthRemoteDataSource {

    fun createToken(body: RequestBody): Flow<TokenResponse>

    fun signup(body: RequestBody): Flow<SignupResponse>

}