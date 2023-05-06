package com.nugu.nuguollim.ui.message.detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nugu.nuguollim.common.data.model.template.Template

@Composable
fun MessageDetailRoute(
    navController: NavHostController,
    viewModel: MessageDetailViewModel = hiltViewModel(),
    template: Template,
    onClickTextCopy: (Template) -> Unit = {},
    onClickTextShare: (Template) -> Unit = {},
    onClickTemplateEdit: (Template) -> Unit = {}
) {
    MessageDetailScreen(
        template = template,
        onClickTextCopy = onClickTextCopy,
        onClickTextShare = onClickTextShare,
        onClickTemplateEdit = onClickTemplateEdit
    )
}