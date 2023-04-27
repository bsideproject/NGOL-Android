package com.nugu.social_login.login

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.nugu.social_login.BuildConfig
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class GoogleLogin @AssistedInject constructor() {

    private lateinit var googleSignInClient: GoogleSignInClient

    fun login(
        activity: ComponentActivity,
        launcher: ActivityResultLauncher<Intent>
    ) {
        val option = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(activity, option)

        launcher.launch(googleSignInClient.signInIntent)
    }

    fun getId(context: Context, callback: (String) -> Unit) {
        callback.invoke(GoogleSignIn.getLastSignedInAccount(context)?.id.toString())
    }

    fun logout() {
        googleSignInClient.takeIf { ::googleSignInClient.isInitialized }?.signOut()
    }

    @AssistedFactory
    interface Factory {
        fun create(): GoogleLogin
    }

}