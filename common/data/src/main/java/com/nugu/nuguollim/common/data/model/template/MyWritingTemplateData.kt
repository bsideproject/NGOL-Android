package com.nugu.nuguollim.common.data.model.template

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

data class MyWritingTemplateData(
    val id: Long,
    val target: String,
    val theme: String,
    val content: String,
    val paper: String,
    val templateId: Long,
    val createdAt: String,
    val updatedAt: String
) : Serializable {

    companion object {
        private const val DATE_FORMAT_KOREA = "yyyy-MM-dd"
    }

    val formatDate: String get() = SimpleDateFormat(DATE_FORMAT_KOREA, Locale.KOREA).let { format ->
        SimpleDateFormat(DATE_FORMAT_KOREA, Locale.KOREA).format(format.parse(updatedAt))
    }

}