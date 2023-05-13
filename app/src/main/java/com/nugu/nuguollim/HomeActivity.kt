package com.nugu.nuguollim

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.navigation.HomeNavHost
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NuguollimTheme {
                HomeNavHost(
                    onClickTemplate = { startTemplateDetailActivity(it) }
                )
            }
        }
    }

    private fun startTemplateDetailActivity(template: Template) {
        val intent = Intent(this, MessageActivity::class.java)
        intent.putExtra("template", template)
        startActivity(intent)
    }
}