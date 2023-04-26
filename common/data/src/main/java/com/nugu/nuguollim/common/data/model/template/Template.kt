package com.nugu.nuguollim.common.data.model.template

data class Template(
    val id: Long,
    val content: String,
    val viewCount: Long = 0,
    val target: String = "",
    val theme: String,
    val background: String = "",
    val favorite: Boolean = false
)
