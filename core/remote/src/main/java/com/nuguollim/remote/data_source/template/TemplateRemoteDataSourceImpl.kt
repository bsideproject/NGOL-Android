package com.nuguollim.remote.data_source.template

import com.nuguollim.remote.model.template.AllMyWritingTemplateResponse
import com.nuguollim.remote.model.template.AllTemplateResponse
import com.nuguollim.remote.model.template.FavoriteResponse
import com.nuguollim.remote.model.template.TemplateDataResponse
import com.nuguollim.remote.model.template.WritingResponse
import com.nuguollim.remote.service.template.TemplateService
import javax.inject.Inject

class TemplateRemoteDataSourceImpl @Inject constructor(
    private val templateService: TemplateService
) : TemplateRemoteDataSource {

    override suspend fun getTemplates(
        targetId: Long?,
        themeId: Long?,
        page: Int,
        sort: String,
        keyword: String?
    ): AllTemplateResponse =
        templateService.getTemplates(
            targetId = targetId,
            themeId = themeId,
            page = page,
            sort = sort,
            keyword = keyword,
        )

    override suspend fun addFavorite(id: Long): FavoriteResponse =
        templateService.addFavorite(id)

    override suspend fun removeFavorite(id: Long): FavoriteResponse =
        templateService.removeFavorite(id)

    override suspend fun saveTemplate(
        id: Long?,
        content: String,
        paper: String,
        templateId: Long
    ): WritingResponse =
        templateService.saveTemplate(
            id = id,
            content = content,
            paper = paper,
            templateId = templateId
        )

    override suspend fun getMyWritingTemplates(page: Int): AllMyWritingTemplateResponse =
        templateService.getMyWritingTemplates(page = page)

    override suspend fun getFavoriteTemplates(page: Int): AllTemplateResponse =
        templateService.getFavoriteTemplates(page)

    override suspend fun removeTemplate(id: Long) {
        templateService.removeTemplate(id)
    }

    override suspend fun getTemplate(id: Long): TemplateDataResponse =
        templateService.getTemplate(id)


}