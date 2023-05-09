package com.nugu.nuguollim.ui.message.detail

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.mohamedrejeb.richeditor.model.RichTextPart
import com.mohamedrejeb.richeditor.model.RichTextStyle
import com.mohamedrejeb.richeditor.model.RichTextValue
import com.nugu.nuguollim.common.data.model.template.Template

@Composable
fun MessageDetailRoute(
    navController: NavHostController,
    viewModel: MessageDetailViewModel = hiltViewModel(),
    template: Template,
    onClickTextCopy: (String) -> Unit = {},
    onClickTextShare: (String) -> Unit = {},
    onClickTemplateEdit: () -> Unit = {}
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

    MessageDetailScreen(
        richTextValue = richTextValue,
        target = template.target,
        theme = template.theme,
        onClickTextCopy = onClickTextCopy,
        onClickTextShare = onClickTextShare,
        onClickTemplateEdit = onClickTemplateEdit,
        onClose = { activity.finish() }
    )
}