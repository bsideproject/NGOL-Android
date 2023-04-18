package com.nuguollim.data.repository.auth

import com.nuguollim.data.model.auth.SignUpData
import com.nuguollim.data.model.auth.TokenData
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface AuthRepository {

    fun createToken(body: RequestBody): Flow<TokenData>

    fun signup(body: RequestBody): Flow<SignUpData>

}