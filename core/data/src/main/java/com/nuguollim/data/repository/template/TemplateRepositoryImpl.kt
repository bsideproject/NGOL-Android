package com.nuguollim.data.repository.template

import com.nugu.nuguollim.common.data.model.template.AllTemplate
import com.nugu.nuguollim.common.data.model.template.FavoriteData
import com.nuguollim.remote.data_source.template.TemplateRemoteDataSource
import com.nuguollim.remote.model.template.FavoriteResponse.Companion.asExternalModel
import com.nuguollim.remote.model.template.asExternalModel
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

}