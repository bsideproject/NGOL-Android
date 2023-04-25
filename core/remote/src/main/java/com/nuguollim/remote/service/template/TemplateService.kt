package com.nuguollim.remote.service.template

import com.nuguollim.remote.model.template.AllTemplateResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TemplateService {

    @GET(value = "v1/templates")
    suspend fun getTemplates(
        @Query("keyword") keyword: String? = null,
        @Query("targetId") targetId: Long? = null,
        @Query("themeId") themeId: Long? = null,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 5,
        @Query("sort", encoded = true) sort: String? = null
    ): AllTemplateResponse
}