package com.nuguollim.remote.data_source.template

import com.nuguollim.remote.model.template.AllTemplateResponse

interface TemplateRemoteDataSource {

    suspend fun getTemplates(
        targetId: Long? = null,
        themeId: Long? = null,
        page: Int,
        sort: String,
        keyword: String?
    ): AllTemplateResponse

}