package com.nugu.nuguollim

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.ui.guide.GuideScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NuguollimTheme {
                GuideScreen {
                    startMainActivity()
                }
            }
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}