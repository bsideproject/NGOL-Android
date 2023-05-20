package com.nuguollim.data.repository.template

import com.nugu.nuguollim.common.data.model.template.AllMyWritingTemplateData
import com.nugu.nuguollim.common.data.model.template.AllTemplate
import com.nugu.nuguollim.common.data.model.template.FavoriteData
import com.nugu.nuguollim.common.data.model.template.Writing
import com.nuguollim.remote.data_source.template.TemplateRemoteDataSource
import com.nuguollim.remote.model.template.FavoriteResponse.Companion.asExternalModel
import com.nuguollim.remote.model.template.asExternalModel
import com.nuguollim.remote.model.template.asExternalResponse
import javax.inject.Inject

class TemplateRepositoryImpl @Inject constructor(
    private val templateRemoteDataSource: TemplateRemoteDataSource
) : TemplateRepository {

    override suspend fun getTemplates(
        page: Int,
        sort: String,
        keyword: String?,
        themeId: Long?,
        targetId: Long?
    ): AllTemplate =
        templateRemoteDataSource
            .getTemplates(
                targetId = targetId,
                themeId = themeId,
                page = page,
                sort = sort,
                keyword = keyword
            )
            .asExternalModel()

    override suspend fun addFavorite(id: Long): FavoriteData =
        templateRemoteDataSource.addFavorite(id).asExternalModel()

    override suspend fun removeFavorite(id: Long): FavoriteData =
        templateRemoteDataSource.removeFavorite(id).asExternalModel()

    override suspend fun saveTemplate(writing: Writing): Writing =
        templateRemoteDataSource.saveTemplate(
            id = writing.id,
            content = writing.content,
            paper = writing.paper,
            templateId = writing.templateId,
        ).asExternalResponse()

    override suspend fun getMyWritingTemplates(page: Int): AllMyWritingTemplateData =
        templateRemoteDataSource.getMyWritingTemplates(page).asExternalModel()

    override suspend fun getFavoriteTemplates(page: Int): AllTemplate =
        templateRemoteDataSource.getFavoriteTemplates(page).asExternalModel()

}