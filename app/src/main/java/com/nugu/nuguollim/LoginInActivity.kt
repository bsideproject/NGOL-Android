package com.nugu.nuguollim

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nugu.nuguollim.ui.login.LoginRoute
import com.nugu.nuguollim.ui.theme.NuguollimTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NuguollimTheme {
                LoginRoute{
                    val intent = Intent(this, SignUpActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(intent)

                    finish()
                }
            }
        }
    }
}