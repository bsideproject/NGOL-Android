package com.nuguollim.remote.model.template

import com.nugu.nuguollim.common.data.model.template.Template

data class TemplateResponse(
    val id: Long,
    val content: String,
    val viewCount: Long,
    val target: String,
    val theme: String,
    val favorite: Boolean
)

fun TemplateResponse.asExternalModel() = Template(
    id = id,
    content = content,
    viewCount = viewCount,
    target = target,
    theme = theme,
    favorite = favorite
)
