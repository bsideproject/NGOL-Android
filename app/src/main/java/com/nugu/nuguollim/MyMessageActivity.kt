package com.nugu.nuguollim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nugu.my_detail.MyMessageDetailScreen
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.util.MediaUtil.saveImage
import com.nugu.nuguollim.util.MediaUtil.shareImage
import com.nugu.nuguollim.util.getMyWritingTemplate
import com.nugu.nuguollim.util.startTemplateDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyMessageActivity : ComponentActivity() {

    private val myMessageViewModel: MyMessageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myWritingTemplateData = getMyWritingTemplate()

        setContent {
            NuguollimTheme {
                MyMessageDetailScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    myWritingTemplateData = myWritingTemplateData,
                    onDeleteTemplate = { id ->
                        myMessageViewModel.removeTemplate(
                            id = id,
                            success = { finish() },
                            fail = { it.printStackTrace() }
                        )
                    },
                    onMovePageMessageEdit = { myWritingTemplateData ->
                        startTemplateDetailActivity(myWritingTemplateData)
                        finish()
                    },
                    onDismissRequest = { finish() },
                    onClickImageSave = { saveImage(it) },
                    onClickImageShare = { shareImage(it) }
                )
            }
        }
    }
}