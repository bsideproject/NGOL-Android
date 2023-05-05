package com.nugu.nuguollim.common.data.model.template

import java.io.Serializable

data class Template(
    val id: Long = 0,
    val content: String = "",
    val viewCount: Long = 0,
    val target: String = "",
    val theme: String = "",
    val favorite: Boolean = false
): Serializable