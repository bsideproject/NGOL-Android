package com.nuguollim.remote.model.search.target

data class TemplateTargetResponse(
    val id: Int,
    val name: String,
    val children: List<TemplateTargetResponse>,
    val depth: Int,
    val themes: List<ThemeResponse>
)
