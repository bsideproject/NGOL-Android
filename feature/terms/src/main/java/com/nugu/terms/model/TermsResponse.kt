package com.nugu.terms.model

import com.google.gson.annotations.SerializedName
import com.nuguollim.data.model.Terms

data class TermsResponse(
    @SerializedName("active") val active: Boolean,
    @SerializedName("context") val context: String,
    @SerializedName("id") val id: Long,
    @SerializedName("termsType") val termsType: String,
    @SerializedName("title") val title: String,
)

fun TermsResponse.asExternalModel() = Terms(
    active = active,
    context = context,
    id = id,
    termsType = termsType,
    title = title
)