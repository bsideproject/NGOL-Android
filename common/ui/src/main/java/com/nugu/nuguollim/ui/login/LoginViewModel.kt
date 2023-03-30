package com.nugu.nuguollim.ui.login

import androidx.lifecycle.ViewModel
import com.nugu.social_login.login.GoogleLogin
import com.nugu.social_login.login.KakaoLogin
import com.nugu.social_login.login.NaverLogin
import com.nugu.social_login.login.SocialLoginFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val socialLoginFactory: SocialLoginFactory
) : ViewModel() {
    /** 소셜 로그인 */
    val kakaoLogin: KakaoLogin = socialLoginFactory.createKakaoLogin()
    val naverLogin: NaverLogin = socialLoginFactory.createNaverLogin()
    val googleLogin: GoogleLogin = socialLoginFactory.createGoogleLogin()
}