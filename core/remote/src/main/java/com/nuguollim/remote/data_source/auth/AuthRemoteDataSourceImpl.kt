package com.nuguollim.remote.data_source.auth

import com.nuguollim.remote.service.auth.AuthService
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import org.json.JSONObject
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataSource {

    override fun createToken(body: RequestBody): Flow<JSONObject> =
        authService.createToken(body)

}