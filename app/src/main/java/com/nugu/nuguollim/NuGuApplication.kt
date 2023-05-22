package com.nugu.nuguollim

import android.app.Application
import com.google.firebase.FirebaseApp
import com.nugu.social_login.login.KakaoLogin
import com.nugu.social_login.login.NaverLogin
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NuGuApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        KakaoLogin.initKakaoSdk(this)
        NaverLogin.initNaverIdLoginSDK(this)
    }

}