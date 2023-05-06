package com.nugu.nuguollim.ui.message.edit

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.mohamedrejeb.richeditor.model.RichTextValue
import com.nugu.nuguollim.common.data.model.template.Template

@Composable
fun MessageEditRoute(
    navController: NavHostController,
    viewModel: MessageEditViewModel = hiltViewModel(),
    template: Template,
    onClickTextCopy: (String) -> Unit = {},
    onClickTextShare: (String) -> Unit = {},
) {
    val activity = LocalContext.current as ComponentActivity
    val richTextValue = Gson().fromJson(template.content, RichTextValue::class.java)
        ?: RichTextValue()

    MessageEditScreen(
        richTextValue = richTextValue,
        target = template.target,
        theme = template.theme,
        onClickTextCopy = onClickTextCopy,
        onClickTextShare = onClickTextShare,
        onClose = { activity.finish() }
    )
}