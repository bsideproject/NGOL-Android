package com.nuguollim.data.usecase.auth

import com.nuguollim.data.repository.splash.SplashRepository
import javax.inject.Inject

class GetFirstInitInfoUseCase @Inject constructor(
    private val splashRepository: SplashRepository
) {

    suspend fun run(): Boolean = splashRepository.getFirstInit()

    suspend operator fun invoke() = run()

}