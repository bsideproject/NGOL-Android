package com.nuguollim.remote.service.template

import com.nugu.config.AuthConfig
import com.nuguollim.remote.model.template.AllTemplateResponse
import com.nuguollim.remote.model.template.FavoriteResponse
import com.nuguollim.remote.model.template.WritingResponse
import retrofit2.http.*

interface TemplateService {

    @GET(value = "v1/templates")
    suspend fun getTemplates(
        @Query("keyword") keyword: String? = null,
        @Query("targetId") targetId: Long? = null,
        @Query("themeId") themeId: Long? = null,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 5,
        @Query("sort", encoded = true) sort: String? = null,
        @Header("Authorization") accessToken: String = "Bearer ${AuthConfig.token}"
    ): AllTemplateResponse

    @GET(value = "v1/templates/{id}/favorite")
    suspend fun addFavorite(
        @Path("id") id: Long,
        @Header("Authorization") accessToken: String = "Bearer ${AuthConfig.token}"
    ): FavoriteResponse

    @GET(value = "v1/templates/{id}/unfavorite")
    suspend fun removeFavorite(
        @Path("id") id: Long,
        @Header("Authorization") accessToken: String = "Bearer ${AuthConfig.token}"
    ): FavoriteResponse

    @PUT(value = "v1/templates/writing")
    suspend fun saveTemplate(
        @Query("id") id: Long?,
        @Query(value = "content", encoded = true) content: String,
        @Query(value = "paper", encoded = true) paper: String,
        @Query("templateId") templateId: Long,
        @Header("Authorization") accessToken: String = "Bearer ${AuthConfig.token}"
    ): WritingResponse
}