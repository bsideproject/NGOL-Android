package com.nuguollim.remote.service.auth

import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("v1/auth/token/create")
    fun createToken(@Body body: RequestBody): Flow<JSONObject>

}