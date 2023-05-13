package com.nugu.nuguollim

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.ui.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NuguollimTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    SplashScreen { isMoveGuideScreen ->
                        if (isMoveGuideScreen) {
                            startGuideActivity()
                        } else {
                            startMainActivity()
                        }
                    }
                }
            }
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun startGuideActivity() {
        val intent = Intent(this, GuideActivity::class.java)
        startActivity(intent)
    }
}