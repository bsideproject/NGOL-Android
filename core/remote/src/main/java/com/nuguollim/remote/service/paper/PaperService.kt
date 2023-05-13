package com.nuguollim.remote.service.paper

import com.nugu.config.AuthConfig
import com.nuguollim.remote.model.paper.AllPaperResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header

interface PaperService {
    @GET("v1/papers")
    fun getPapers(
        @Header("Authorization") accessToken: String = "Bearer ${AuthConfig.token}"
    ): Flow<AllPaperResponse>
}