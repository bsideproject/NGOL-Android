package com.nuguollim.remote.data_source.auth

import com.nuguollim.remote.model.auth.SignupResponse
import com.nuguollim.remote.model.auth.TokenResponse
import com.nuguollim.remote.service.auth.AuthService
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataSource {

    override fun createToken(body: RequestBody): Flow<TokenResponse> =
        authService.createToken(body)

    override fun signup(body: RequestBody): Flow<SignupResponse> =
        authService.signup(body)

}