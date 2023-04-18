package com.nuguollim.remote.model.terms

import com.google.gson.annotations.SerializedName
import com.nugu.nuguollim.common.data.model.terms.SingleTerms

data class SingleTermsResponse(
    @SerializedName("message") val message: String,
    @SerializedName("count") val count: Long,
    @SerializedName("data") val data: TermsResponse,
)

fun SingleTermsResponse.asExternalModel() = SingleTerms(
    message = message,
    count = count,
    data = data.asExternalModel()
)
