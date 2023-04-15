package com.nuguollim.remote.data_source.auth

import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import org.json.JSONObject

interface AuthRemoteDataSource {

    fun createToken(body: RequestBody): Flow<JSONObject>

}