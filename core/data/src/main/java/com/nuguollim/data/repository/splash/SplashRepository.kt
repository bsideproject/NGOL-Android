package com.nuguollim.data.repository.splash

interface SplashRepository {

    suspend fun getFirstInit(): Boolean

    suspend fun setFirstInit(isInit: Boolean)

}