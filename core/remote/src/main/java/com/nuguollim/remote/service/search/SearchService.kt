package com.nuguollim.remote.service.search

import com.nugu.config.AuthConfig
import com.nuguollim.remote.model.search.target.AllTemplateTargetResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header

interface SearchService {

    @GET("v1/templates/targets")
    fun getTemplateTargets(
        @Header("Authorization") accessToken: String = "Bearer ${AuthConfig.token}"
    ): Flow<AllTemplateTargetResponse>

}