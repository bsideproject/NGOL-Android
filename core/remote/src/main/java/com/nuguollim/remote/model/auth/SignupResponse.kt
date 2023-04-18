package com.nuguollim.remote.model.auth

data class SignupResponse(
    val id: Int,
    val providerType: String,
    val providerId: String,
    val username: String,
    val nickname: String,
)