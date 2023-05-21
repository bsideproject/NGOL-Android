package com.nugu.nuguollim

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.navigation.SignUpNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NuguollimTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    SignUpNavHost(
                        modifier = Modifier.padding(it)
                    ) {
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                Intent.FLAG_ACTIVITY_SINGLE_TOP
                        startActivity(intent)

                        finish()
                    }
                }
            }
        }
    }
}