package com.nuguollim.data.usecase.auth

import com.nuguollim.data.repository.splash.SplashRepository
import javax.inject.Inject

class SetFirstInitInfoUseCase @Inject constructor(
    private val splashRepository: SplashRepository
) {

    suspend operator fun invoke(isInit: Boolean) = run(isInit)

    suspend fun run(isInit: Boolean) {
        splashRepository.setFirstInit(isInit)
    }

}