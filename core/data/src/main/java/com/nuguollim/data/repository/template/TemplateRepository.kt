package com.nuguollim.data.repository.template

import com.nugu.nuguollim.common.data.model.template.AllTemplate

interface TemplateRepository {

    suspend fun getTemplates(
        page: Int,
        sort: String,
        keyword: String? = null,
        themeId: Long? = null,
        targetId: Long? = null
    ): AllTemplate

}