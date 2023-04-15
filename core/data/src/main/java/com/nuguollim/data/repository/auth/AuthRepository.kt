package com.nuguollim.data.repository.auth

import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import org.json.JSONObject

interface AuthRepository {

    fun createToken(body: RequestBody): Flow<JSONObject>

}