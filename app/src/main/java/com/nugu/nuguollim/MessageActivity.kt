package com.nugu.nuguollim

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.common.data.model.template.Writing
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.navigation.MessageNavHost
import com.nugu.nuguollim.util.MediaUtil.saveImage
import com.nugu.nuguollim.util.MediaUtil.shareImage
import com.nugu.nuguollim.util.MediaUtil.shareText
import com.nugu.nuguollim.util.getMyWritingTemplate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageActivity : ComponentActivity() {

    private val viewModel: MessageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Scaffold(
            ) { innerPadding ->
                NuguollimTheme {
                    if (intent.hasExtra("template")) {
                        MessageNavHost(
                            modifier = Modifier.padding(innerPadding),
                            navHostController = navController,
                            template = getTemplate(),
                            onClickTextCopy = { setClipboardText(it) },
                            onClickTextShare = { shareText(it) },
                            onClickImageSave = { imageBitmap, writing ->
                                saveImage(imageBitmap)
                                saveImageRemote(writing)
                            },
                            onClickImageShare = { shareImage(it) }
                        )
                    } else if (intent.hasExtra("myWritingTemplateData")) {
                        MessageNavHost(
                            modifier = Modifier.padding(innerPadding),
                            navHostController = navController,
                            myWritingTemplateData = getMyWritingTemplate(),
                            onClickTextCopy = { setClipboardText(it) },
                            onClickTextShare = { shareText(it) },
                            onClickImageSave = { imageBitmap, writing ->
                                saveImage(imageBitmap)
                                saveImageRemote(writing)
                            },
                            onClickImageShare = { shareImage(it) }
                        )
                    }
                }
            }
        }
    }

    private fun setClipboardText(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("nugu message content", text)
        clipboard.setPrimaryClip(clip)
    }

    private fun getTemplate(): Template {
        val template = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("template", Template::class.java)
        } else {
            intent.getSerializableExtra("template") as Template
        }

        // 조회수 증가
        template?.id?.let { viewModel.addTemplateCount(it) }

        return template ?: Template()
    }

    private fun saveImageRemote(writing: Writing) {
        viewModel.saveTemplate(writing)
    }
}