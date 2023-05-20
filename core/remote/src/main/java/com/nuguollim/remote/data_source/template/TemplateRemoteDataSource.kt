package com.nuguollim.remote.data_source.template

import com.nuguollim.remote.model.template.AllMyWritingTemplateResponse
import com.nuguollim.remote.model.template.AllTemplateResponse
import com.nuguollim.remote.model.template.FavoriteResponse

interface TemplateRemoteDataSource {

    suspend fun getTemplates(
        targetId: Long? = null,
        themeId: Long? = null,
        page: Int,
        sort: String,
        keyword: String?
    ): AllTemplateResponse

    suspend fun addFavorite(id: Long): FavoriteResponse

    suspend fun removeFavorite(id: Long): FavoriteResponse

    suspend fun getMyWritingTemplates(page: Int): AllMyWritingTemplateResponse

    suspend fun getFavoriteTemplates(page: Int): AllTemplateResponse

}