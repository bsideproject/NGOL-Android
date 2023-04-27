package com.nuguollim.data.model.search.target

import com.nuguollim.data.model.search.target.ThemeData.Companion.asExternalModel
import com.nuguollim.remote.model.search.target.TemplateTargetResponse
import java.io.Serializable

data class TemplateTargetData(
    val id: Int,
    val name: String,
    val children: List<TemplateTargetData>,
    val depth: Int,
    val themes: List<ThemeData>
) : Serializable {
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