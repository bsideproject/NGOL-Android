package com.nuguollim.remote.data_source.auth

import com.nuguollim.remote.model.auth.NickNameResponse
import com.nuguollim.remote.model.auth.SignupResponse
import com.nuguollim.remote.model.auth.TokenResponse
import com.nuguollim.remote.model.auth.UserResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface AuthRemoteDataSource {

    fun createToken(body: RequestBody): Flow<TokenResponse>

    fun signup(body: RequestBody): Flow<SignupResponse>

    fun getMyUserData(): Flow<UserResponse>

    suspend fun unregister()

    suspend fun setNickName(nickname: String): NickNameResponse

}