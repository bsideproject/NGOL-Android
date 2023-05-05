package com.nugu.ui_core

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString

object StringUtil {

    fun spannableAll(
        text: String,
        keyword: String,
        style: SpanStyle
    ): AnnotatedString = buildAnnotatedString {
        var startIndex = text.indexOf(keyword)
        append(text)

        while (startIndex >= 0) {
            val endIndex = startIndex + keyword.length
            addStyle(style, startIndex, endIndex)
            startIndex = text.indexOf(keyword, endIndex)
        }
    }

}