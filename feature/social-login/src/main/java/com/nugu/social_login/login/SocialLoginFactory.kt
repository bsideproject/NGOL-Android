package com.nugu.social_login.login

import javax.inject.Inject

class SocialLoginFactory @Inject constructor(
    private val googleLoginFactory: GoogleLogin.Factory,
    private val naverLoginFactory: NaverLogin.Factory,
    private val kakaoLoginFactory: KakaoLogin.Factory,
) {

    fun createGoogleLogin(): GoogleLogin = googleLoginFactory.create()

    fun createNaverLogin(): NaverLogin = naverLoginFactory.create()

    fun createKakaoLogin(): KakaoLogin = kakaoLoginFactory.create()

}