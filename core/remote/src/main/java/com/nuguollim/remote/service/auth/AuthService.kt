package com.nuguollim.remote.service.auth

import com.nugu.config.AuthConfig
import com.nuguollim.remote.model.auth.NickNameResponse
import com.nuguollim.remote.model.auth.SignupResponse
import com.nuguollim.remote.model.auth.TokenResponse
import com.nuguollim.remote.model.auth.UserResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface AuthService {

    @POST("v1/auth/token/create")
    fun createToken(@Body body: RequestBody): Flow<TokenResponse>

    @POST("v1/users/signup")
    fun signup(@Body body: RequestBody): Flow<SignupResponse>

    @GET("v1/users/detail")
    fun getMyUserData(
        @Header("Authorization") accessToken: String = "Bearer ${AuthConfig.token}"
    ): Flow<UserResponse>

    @DELETE("v1/users/withdrawal")
    suspend fun unregister(
        @Header("Authorization") accessToken: String = "Bearer ${AuthConfig.token}"
    ): Response<Void>

    @PUT("v1/users")
    suspend fun setNickName(
        @Query("nickname") nickname: String,
        @Header("Authorization") accessToken: String = "Bearer ${AuthConfig.token}"
    ): NickNameResponse

}