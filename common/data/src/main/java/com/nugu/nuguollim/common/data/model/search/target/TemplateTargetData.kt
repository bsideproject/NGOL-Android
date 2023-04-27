package com.nugu.nuguollim.common.data.model.search.target

import java.io.Serializable

data class TemplateTargetData(
    val id: Int,
    val name: String,
    val children: List<TemplateTargetData>,
    val depth: Int,
    val themes: List<ThemeData>
) : Serializable