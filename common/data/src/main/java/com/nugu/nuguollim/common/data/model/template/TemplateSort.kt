package com.nugu.nuguollim.common.data.model.template

enum class TemplateSort(
    val value: String,
    val sortText: String,
    val title: String
) {
    VIEW("인기순", "viewCount,desc", "올리머에 지금 가장 HOT한 템플릿"),
    NEW("최신순", "createdAt,desc", "누구올림 신상 NEW 템플릿"),
}