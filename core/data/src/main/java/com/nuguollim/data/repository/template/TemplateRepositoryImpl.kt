package com.nuguollim.data.repository.template

import com.nugu.nuguollim.common.data.model.template.AllTemplate
import com.nuguollim.remote.data_source.template.TemplateRemoteDataSource
import com.nuguollim.remote.model.template.asExternalModel
import javax.inject.Inject

class TemplateRepositoryImpl @Inject constructor(
    private val templateRemoteDataSource: TemplateRemoteDataSource
) : TemplateRepository {

    override suspend fun getTemplates(page: Int, sort: String, keyword: String): AllTemplate =
        templateRemoteDataSource
            .getTemplates(page = page, sort = sort, keyword = keyword)
            .asExternalModel()
}