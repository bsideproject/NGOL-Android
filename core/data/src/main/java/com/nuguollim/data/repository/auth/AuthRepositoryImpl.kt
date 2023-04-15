package com.nuguollim.data.repository.auth

import com.nuguollim.remote.data_source.auth.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import org.json.JSONObject
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override fun createToken(body: RequestBody): Flow<JSONObject> =
        authRemoteDataSource.createToken(body)

}