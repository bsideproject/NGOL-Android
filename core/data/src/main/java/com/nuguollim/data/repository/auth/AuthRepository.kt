package com.nuguollim.data.repository.auth

import com.nugu.nuguollim.common.data.model.auth.AuthInfo
import com.nugu.nuguollim.common.data.model.auth.NickNameData
import com.nugu.nuguollim.common.data.model.auth.SignUpData
import com.nugu.nuguollim.common.data.model.auth.TokenData
import com.nugu.nuguollim.common.data.model.auth.UserData
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface AuthRepository {

    fun createToken(body: RequestBody): Flow<TokenData>

    fun signup(body: RequestBody): Flow<SignUpData>

    fun getAuthInfo(): Flow<AuthInfo>

    suspend fun setAuthInfo(provideType: String, provideId: String)

    suspend fun clearAuthInfo()

    fun getProvideToken(): Flow<String?>

    suspend fun setProvideToken(token: String)

    fun getMyUserData(): Flow<UserData>

    suspend fun unregister()

    suspend fun setNickName(nickname: String): NickNameData

}