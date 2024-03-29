package com.nuguollim.remote.model.terms

import com.google.gson.annotations.SerializedName
import com.nugu.nuguollim.common.data.model.terms.Terms

data class TermsResponse(
    @SerializedName("active") val active: Boolean,
    @SerializedName("link") val link: String,
    @SerializedName("id") val id: Long,
    @SerializedName("termsType") val termsType: String,
    @SerializedName("title") val title: String,
)

fun TermsResponse.asExternalModel() = Terms(
    active = active,
    link = link,
    id = id,
    termsType = termsType,
    title = title
)