package com.nugu.nuguollim.common.data.model.terms

data class AllTerms(
    val message: String,
    val count: Long,
    val data: List<Terms>,
)