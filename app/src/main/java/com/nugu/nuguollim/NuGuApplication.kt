package com.nugu.nuguollim

import android.app.Application
import com.nugu.social_login.login.KakaoLogin
import com.nugu.social_login.login.NaverLogin
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NuGuApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoLogin.initKakaoSdk(this)
        NaverLogin.initNaverIdLoginSDK(this)
    }

}