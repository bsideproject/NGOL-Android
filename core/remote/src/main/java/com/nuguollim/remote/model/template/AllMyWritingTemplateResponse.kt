package com.nuguollim.remote.model.template

import com.nugu.nuguollim.common.data.model.template.AllMyWritingTemplateData
import com.nuguollim.remote.model.paging.PagingResponse
import com.nuguollim.remote.model.paging.asExternalModel

data class AllMyWritingTemplateResponse(
    val message: String,
    val count: Long,
    val data: PagingResponse<MyWritingTemplateResponse>
)

fun AllMyWritingTemplateResponse.asExternalModel() = AllMyWritingTemplateData(
    message = message,
    count = count,
    data = data.asExternalModel()
)