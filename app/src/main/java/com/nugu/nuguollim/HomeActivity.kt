package com.nugu.nuguollim

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nugu.nuguollim.common.data.model.template.MyWritingTemplateData
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.navigation.HomeNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NuguollimTheme {
                HomeNavHost(
                    onClickTemplate = { startTemplateDetailActivity(it) },
                    onClickMyWritingTemplate = { startTemplateDetailActivity(it) },
                    onMoveDetailScreen = { startMyTemplateDetailActivity(it) },
                )
            }
        }
    }

    private fun startTemplateDetailActivity(template: Template) {
        val intent = Intent(this, MessageActivity::class.java)
        intent.putExtra("template", template)
        startActivity(intent)
    }

    private fun startTemplateDetailActivity(myWritingTemplateData: MyWritingTemplateData) {
        val intent = Intent(this, MessageActivity::class.java)
        intent.putExtra("myWritingTemplateData", myWritingTemplateData)
        startActivity(intent)
    }

    private fun startMyTemplateDetailActivity(myWritingTemplateData: MyWritingTemplateData) {
        val intent = Intent(this, MyMessageActivity::class.java)
        intent.putExtra("myWritingTemplateData", myWritingTemplateData)
        startActivity(intent)
    }
}