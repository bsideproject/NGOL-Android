package com.nuguollim.data.usecase.auth

sealed interface AuthProvide {

    @JvmInline
    value class Type(val data: String) : AuthProvide

    @JvmInline
    value class Id(val data: String) : AuthProvide

    @JvmInline
    value class Name(val data: String) : AuthProvide

}
