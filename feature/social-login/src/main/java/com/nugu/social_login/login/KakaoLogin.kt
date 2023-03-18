package com.nugu.social_login.login

import android.app.Activity
import android.content.Context
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.user.UserApiClient
import com.nugu.social_login.BuildConfig
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class KakaoLogin @AssistedInject constructor() {

    companion object {
        fun initKakaoSdk(
            context: Context,
            appKey: String = BuildConfig.KAKAO_NATIVE_APP_KEY
        ) {
            KakaoSdk.init(context, appKey)
        }
    }

    fun login(activity: Activity, callback: (token: String?, error: Throwable?) -> Unit) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(activity)) {
            UserApiClient.instance.loginWithKakaoTalk(
                context = activity,
                callback = { oAuthToken, error -> callback.invoke(oAuthToken?.accessToken, error) }
            )
        } else {
            UserApiClient.instance.loginWithKakaoAccount(
                context = activity,
                callback = { oAuthToken, error -> callback.invoke(oAuthToken?.accessToken, error) }
            )
        }
    }

    fun logout(callback: (Throwable?) -> Unit) {
        UserApiClient.instance.logout(callback)
    }

    @AssistedFactory
    interface Factory {
        fun create(): KakaoLogin
    }

}