package com.nuguollim.remote.data_source.template

import com.nuguollim.remote.model.template.AllTemplateResponse

interface TemplateRemoteDataSource {

    suspend fun getTemplates(page: Int, sort: String, keyword: String?): AllTemplateResponse
}