package com.nuguollim.data.repository.auth

import com.nugu.data_store.data_source.auth.AuthLocalDataSource
import com.nugu.nuguollim.common.data.model.auth.AuthInfo
import com.nugu.nuguollim.common.data.model.auth.SignUpData
import com.nugu.nuguollim.common.data.model.auth.TokenData
import com.nuguollim.remote.data_source.auth.AuthRemoteDataSource
import com.nuguollim.remote.model.auth.SignupResponse.Companion.asExternalModel
import com.nuguollim.remote.model.auth.TokenResponse.Companion.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.RequestBody
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {

    override fun createToken(body: RequestBody): Flow<TokenData> =
        authRemoteDataSource.createToken(body).map { it.asExternalModel() }

    override fun signup(body: RequestBody): Flow<SignUpData> =
        authRemoteDataSource.signup(body).map { it.asExternalModel() }

    override fun getAuthInfo(): Flow<AuthInfo> = authLocalDataSource.getAuthInfo().map { authInfo ->
        AuthInfo(
            provideType = authInfo.provideType,
            provideId = authInfo.provideId,
        )
    }

    override suspend fun setAuthInfo(provideType: String, provideId: String) {
        authLocalDataSource.setAuthInfo(provideType, provideId)
    }

    override fun getProvideToken(): Flow<String?> = authLocalDataSource.getProvideToken()

    override suspend fun setProvideToken(token: String) {
        authLocalDataSource.setProvideToken(token)
    }

}