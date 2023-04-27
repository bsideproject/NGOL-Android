package com.nugu.nuguollim.common.data.model.auth

data class SignUpData(
    val id: Int,
    val providerType: String,
    val providerId: String,
    val nickname: String,
)