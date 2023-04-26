package com.nuguollim.remote.model.template

import com.nugu.nuguollim.common.data.model.template.AllTemplate
import com.nuguollim.remote.model.paging.PagingResponse
import com.nuguollim.remote.model.paging.asExternalModel

data class AllTemplateResponse(
    val message: String,
    val count: Long,
    val data: PagingResponse<TemplateResponse>
)

fun AllTemplateResponse.asExternalModel() = AllTemplate(
    message = message,
    count = count,
    data = data.asExternalModel()
)