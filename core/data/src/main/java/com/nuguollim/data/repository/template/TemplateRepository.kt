package com.nuguollim.data.repository.template

import com.nugu.nuguollim.common.data.model.template.*

interface TemplateRepository {

    suspend fun getTemplates(
        page: Int,
        sort: String,
        keyword: String? = null,
        themeId: Long? = null,
        targetId: Long? = null
    ): AllTemplate

    suspend fun addFavorite(id: Long): FavoriteData

    suspend fun addCount(id: Long): Boolean

    suspend fun removeFavorite(id: Long): FavoriteData

    suspend fun saveTemplate(writing: Writing): Writing

    suspend fun getMyWritingTemplates(page: Int): AllMyWritingTemplateData

    suspend fun getFavoriteTemplates(page: Int): AllTemplate

    suspend fun removeTemplate(id: Long)

    suspend fun getTemplate(id: Long): Template

}