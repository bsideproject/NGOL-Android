package com.nugu.social_login.login

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthBehavior
import com.nugu.social_login.BuildConfig
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class NaverLogin @AssistedInject constructor() {

    companion object {
        fun initNaverIdLoginSDK(
            context: Context,
            clientId: String = BuildConfig.NAVER_CLIENT_ID,
            clientSecret: String = BuildConfig.NAVER_CLIENT_SECRET,
            clientName: String = "nuguollim"
        ) {
            NaverIdLoginSDK.apply {
                showDevelopersLog(true)
                initialize(context, clientId, clientSecret, clientName)
                isShowMarketLink = true
                isShowBottomTab = true
            }
        }
    }

    fun login(
        activity: ComponentActivity,
        launcher: ActivityResultLauncher<Intent>
    ) {
        NaverIdLoginSDK.behavior = NidOAuthBehavior.DEFAULT
        NaverIdLoginSDK.authenticate(activity, launcher)
    }

    fun logout() {
        NaverIdLoginSDK.logout()
    }

    @AssistedFactory
    interface Factory {
        fun create(): NaverLogin
    }

}