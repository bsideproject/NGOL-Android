package com.nugu.nuguollim.common.data.model.template

data class HomeTemplate(
    val id: Long,
    val content: String,
    val theme: String,
    val viewCount: Long = 0,
    val date: Long = 0
)
