package com.nugu.data_store.data_source.splash

interface SplashLocalDataSource {

    suspend fun getFirstInit(): Boolean

    suspend fun setFirstInit(isInit: Boolean)

}