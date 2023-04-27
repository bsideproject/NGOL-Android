package com.nugu.social_login.login

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthBehavior
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import com.nugu.social_login.BuildConfig
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Job

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

    /** 프로필 정보 갱신 Coroutine Job */
    private var callProfileApiJob: Job? = null

    fun login(
        activity: ComponentActivity,
        launcher: ActivityResultLauncher<Intent>
    ) {
        NaverIdLoginSDK.behavior = NidOAuthBehavior.DEFAULT
        NaverIdLoginSDK.authenticate(activity, launcher)
    }

    fun getId(
        onError: ((errorCode: Int, message: String) -> Unit)? = null,
        onFail: ((httpStatus: Int, message: String) -> Unit)? = null,
        onResult: (String) -> Unit
    ) {
        callProfileApiJob?.takeIf { it.isActive }?.cancel()
        callProfileApiJob =
            NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
                override fun onError(errorCode: Int, message: String) {
                    onError?.invoke(errorCode, message)
                }

                override fun onFailure(httpStatus: Int, message: String) {
                    onFail?.invoke(httpStatus, message)
                }

                override fun onSuccess(result: NidProfileResponse) {
                    onResult.invoke(result.profile?.id.toString())
                }
            })
    }

    fun logout() {
        NaverIdLoginSDK.logout()
    }

    @AssistedFactory
    interface Factory {
        fun create(): NaverLogin
    }

}