package com.nuguollim.remote.model.paging

import com.google.gson.annotations.SerializedName
import com.nugu.nuguollim.common.data.model.paging.Paging
import com.nugu.nuguollim.common.data.model.template.MyWritingTemplateData
import com.nugu.nuguollim.common.data.model.template.Template
import com.nuguollim.remote.model.template.MyWritingTemplateResponse
import com.nuguollim.remote.model.template.MyWritingTemplateResponse.Companion.asExternalModel
import com.nuguollim.remote.model.template.TemplateResponse
import com.nuguollim.remote.model.template.asExternalModel

data class PagingResponse<T>(
    @SerializedName("content") val content: List<T>,
    @SerializedName("pageable") val pageable: PageableResponse,
    @SerializedName("size") val size: Int,
    @SerializedName("number") val number: Int,
    @SerializedName("sort") val sort: SortResponse,
    @SerializedName("first") val first: Boolean,
    @SerializedName("last") val last: Boolean,
    @SerializedName("numberOfElements") val numberOfElements: Int,
    @SerializedName("empty") val empty: Boolean
)

fun PagingResponse<TemplateResponse>.asExternalModel(): Paging<Template> {
    val mappedContent = content.map { response -> response.asExternalModel() }

    return Paging(
        content = mappedContent,
        pageable = pageable.asExternalModel(),
        size = size,
        number = number,
        sort = sort.asExternalModel(),
        first = first,
        last = last,
        numberOfElements = numberOfElements,
        empty = empty
    )
}

@JvmName("asExternalModelV2")
fun PagingResponse<MyWritingTemplateResponse>.asExternalModel(): Paging<MyWritingTemplateData> {
    return Paging(
        content = content.map { it.asExternalModel() },
        pageable = pageable.asExternalModel(),
        size = size,
        number = number,
        sort = sort.asExternalModel(),
        first = first,
        last = last,
        numberOfElements = numberOfElements,
        empty = empty
    )
}