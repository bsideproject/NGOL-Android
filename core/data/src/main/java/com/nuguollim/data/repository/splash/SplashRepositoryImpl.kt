package com.nuguollim.data.repository.splash

import com.nugu.data_store.data_source.splash.SplashLocalDataSource
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(
    private val splashLocalDataSource: SplashLocalDataSource
) : SplashRepository {

    override suspend fun getFirstInit(): Boolean = splashLocalDataSource.getFirstInit()

    override suspend fun setFirstInit(isInit: Boolean) = splashLocalDataSource.setFirstInit(isInit)

}