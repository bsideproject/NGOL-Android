package com.nuguollim.remote.data_source.template

import com.nuguollim.remote.model.template.AllTemplateResponse
import com.nuguollim.remote.model.template.FavoriteResponse
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

}