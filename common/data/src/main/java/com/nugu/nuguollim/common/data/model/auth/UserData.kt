package com.nugu.nuguollim.common.data.model.auth

data class UserData(
    val id: Long,
    val providerType: String,
    val providerId: String,
    val username: String,
    val nickname: String,
)