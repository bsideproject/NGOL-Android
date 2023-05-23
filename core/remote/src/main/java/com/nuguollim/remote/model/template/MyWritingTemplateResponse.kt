package com.nuguollim.remote.model.template

import com.nugu.nuguollim.common.data.model.template.MyWritingTemplateData

data class MyWritingTemplateResponse(
    val id: Long,
    val target: String,
    val theme: String,
    val content: String,
    val paper: String,
    val templateId: Long,
) {
    companion object {
        fun MyWritingTemplateResponse.asExternalModel(): MyWritingTemplateData =
            MyWritingTemplateData(
                id = id,
                target = target,
                theme = theme,
                content = content,
                paper = paper,
                templateId = templateId,
            )
    }
}
