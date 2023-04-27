package com.nugu.nuguollim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nugu.nuguollim.navigation.HomeNavHost
import com.nugu.nuguollim.ui.theme.NuguollimTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NuguollimTheme {
                HomeNavHost()
            }
        }
    }
}