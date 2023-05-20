package com.nugu.nuguollim.common.data.model.template

data class MyWritingTemplateData(
    val deleted: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val id: Long,
    val content: String,
    val paper: String,
    val userId: Long,
    val templateId: Long,
)
