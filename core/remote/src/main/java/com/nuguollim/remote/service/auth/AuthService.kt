package com.nuguollim.remote.service.auth

import com.nuguollim.remote.model.auth.SignupResponse
import com.nuguollim.remote.model.auth.TokenResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("v1/auth/token/create")
    fun createToken(@Body body: RequestBody): Flow<TokenResponse>

    @POST("v1/users/signup")
    fun signup(@Body body: RequestBody): Flow<SignupResponse>

}