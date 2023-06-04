package com.nugu.nuguollim.ui.message.edit

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.mohamedrejeb.richeditor.model.RichTextPart
import com.mohamedrejeb.richeditor.model.RichTextStyle
import com.mohamedrejeb.richeditor.model.RichTextValue
import com.nugu.nuguollim.common.data.model.template.MyWritingTemplateData
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.common.data.model.template.Writing

@Composable
fun MessageEditRoute(
    navController: NavHostController,
    viewModel: MessageEditViewModel = hiltViewModel(),
    template: Template,
    onClickTextCopy: (String) -> Unit = {},
    onClickTextShare: (String) -> Unit = {},
    onClickImageSave: (ImageBitmap, Writing) -> Unit = { _, _ -> },
    onClickImageShare: (ImageBitmap) -> Unit = {},
) {
    val activity = LocalContext.current as ComponentActivity
    val richTextValue = try {
        Gson().fromJson(template.content, RichTextValue::class.java)
    } catch (e: Exception) {
        RichTextValue(
            text = template.content,
            parts = listOf(
                RichTextPart(
                    fromIndex = 0,
                    toIndex = template.content.length,
                    styles = setOf(
                        RichTextStyle.TextColor(Color.Black),
                    )
                )
            )
        )
    }
    val papers by viewModel.papers.collectAsStateWithLifecycle()

    MessageEditScreen(
        richTextValue = richTextValue,
        target = template.target,
        theme = template.theme,
        papers = papers,
        onClickTextCopy = onClickTextCopy,
        onClickTextShare = onClickTextShare,
        onClose = { activity.finish() },
        onClickImageShare = onClickImageShare,
        onClickImageSave = { imageBitmap, writing ->
            writing.templateId = template.id
            onClickImageSave(imageBitmap, writing)
        },
    )
}

@Composable
fun MessageEditRoute(
    navController: NavHostController,
    viewModel: MessageEditViewModel = hiltViewModel(),
    myWritingTemplateData: MyWritingTemplateData,
    onClickTextCopy: (String) -> Unit = {},
    onClickTextShare: (String) -> Unit = {},
    onClickImageSave: (ImageBitmap, Writing) -> Unit = { _, _ -> },
    onClickImageShare: (ImageBitmap) -> Unit = {},
) {
    val activity = LocalContext.current as ComponentActivity
    val richTextValue = try {
        Gson().fromJson(myWritingTemplateData.content, RichTextValue::class.java)
    } catch (e: Exception) {
        RichTextValue(
            text = myWritingTemplateData.content,
            parts = listOf(
                RichTextPart(
                    fromIndex = 0,
                    toIndex = myWritingTemplateData.content.length,
                    styles = setOf(
                        RichTextStyle.TextColor(Color.Black),
                    )
                )
            )
        )
    }
    val papers by viewModel.papers.collectAsStateWithLifecycle()

    MessageEditScreen(
        richTextValue = richTextValue,
        target = myWritingTemplateData.target,
        theme = myWritingTemplateData.theme,
        papers = papers,
        id = myWritingTemplateData.id,
        onClickTextCopy = onClickTextCopy,
        onClickTextShare = onClickTextShare,
        onClose = { activity.finish() },
        onClickImageShare = onClickImageShare,
        onClickImageSave = { imageBitmap, writing ->
            writing.templateId = myWritingTemplateData.templateId
            onClickImageSave(imageBitmap, writing)
        },
    )
}