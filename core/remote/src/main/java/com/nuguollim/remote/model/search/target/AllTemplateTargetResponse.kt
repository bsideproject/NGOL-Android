package com.nuguollim.remote.model.search.target

data class AllTemplateTargetResponse(
    val message: String,
    val count: Int,
    val data: List<TemplateTargetResponse>
)
