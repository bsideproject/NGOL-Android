package com.nuguollim.remote.model.template

import com.google.gson.annotations.SerializedName
import com.nugu.nuguollim.common.data.model.template.Writing

data class WritingResponse(
    @SerializedName("id") val id: Long?,
    @SerializedName("content") val content: String?,
    @SerializedName("paper") val paper: String?,
    @SerializedName("templateId") val templateId: Long?,
)


fun WritingResponse.asExternalResponse() = Writing(
    id = id ?: 0,
    content = content ?: "",
    paper = paper ?: "",
    templateId = templateId ?: 0,
)