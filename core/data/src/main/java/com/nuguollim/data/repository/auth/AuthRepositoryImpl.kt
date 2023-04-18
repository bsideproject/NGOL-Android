package com.nuguollim.data.repository.auth

import com.nuguollim.data.model.auth.SignUpData
import com.nuguollim.data.model.auth.SignUpData.Companion.asExternalModel
import com.nuguollim.data.model.auth.TokenData
import com.nuguollim.data.model.auth.TokenData.Companion.asExternalModel
import com.nuguollim.remote.data_source.auth.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.RequestBody
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override fun createToken(body: RequestBody): Flow<TokenData> =
        authRemoteDataSource.createToken(body).map { it.asExternalModel() }

    override fun signup(body: RequestBody): Flow<SignUpData> =
        authRemoteDataSource.signup(body).map { it.asExternalModel() }

}