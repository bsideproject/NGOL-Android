package com.nuguollim.remote.model.search.target

import com.nugu.nuguollim.common.data.model.search.target.TemplateTargetData
import com.nuguollim.remote.model.search.target.ThemeResponse.Companion.asExternalModel

data class TemplateTargetResponse(
    val id: Int,
    val name: String,
    val children: List<TemplateTargetResponse>,
    val depth: Int,
    val themes: List<ThemeResponse>
) {
    companion object {
        fun TemplateTargetResponse.asExternalModel(): TemplateTargetData = TemplateTargetData(
            id = id,
            name = name,
            children = children.asExternalModel(),
            depth = depth,
            themes = themes.map { it.asExternalModel() }
        )

        fun List<TemplateTargetResponse>.asExternalModel(): List<TemplateTargetData> = map { it.asExternalModel() }
    }
}
