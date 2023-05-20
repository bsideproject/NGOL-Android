package com.nuguollim.remote.model.template

import com.nugu.nuguollim.common.data.model.template.MyWritingTemplateData

data class MyWritingTemplateResponse(
    val deleted: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val id: Long,
    val content: String,
    val paper: String,
    val userId: Long,
    val templateId: Long,
) {
    companion object {
        fun MyWritingTemplateResponse.asExternalModel(): MyWritingTemplateData =
            MyWritingTemplateData(
                deleted = deleted,
                createdAt = createdAt,
                updatedAt = updatedAt,
                id = id,
                content = content,
                paper = paper,
                userId = userId,
                templateId = templateId,
            )
    }
}
