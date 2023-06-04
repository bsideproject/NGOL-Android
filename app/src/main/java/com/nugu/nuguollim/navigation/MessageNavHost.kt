package com.nugu.nuguollim.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nugu.nuguollim.common.data.model.template.MyWritingTemplateData
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.common.data.model.template.Writing
import com.nugu.nuguollim.ui.message.edit.MessageEditRoute

@Composable
fun MessageNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    template: Template,
    onClickTextCopy: (String) -> Unit = {},
    onClickTextShare: (String) -> Unit = {},
    onClickImageSave: (ImageBitmap, Writing) -> Unit = { _, _ -> },
    onClickImageShare: (ImageBitmap) -> Unit = {},
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = MessageScreenType.Edit.screenRoute
    ) {
        composable(MessageScreenType.Edit.screenRoute) {
            MessageEditRoute(
                navHostController,
                template = template,
                onClickTextCopy = { onClickTextCopy(it) },
                onClickTextShare = { onClickTextShare(it) },
                onClickImageSave = { imageBitmap, writing ->
                    onClickImageSave(imageBitmap, writing)
                },
                onClickImageShare = { onClickImageShare(it) },
            )
        }
    }
}

@Composable
fun MessageNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    myWritingTemplateData: MyWritingTemplateData,
    onClickTextCopy: (String) -> Unit = {},
    onClickTextShare: (String) -> Unit = {},
    onClickImageSave: (ImageBitmap, Writing) -> Unit = { _, _ -> },
    onClickImageShare: (ImageBitmap) -> Unit = {},
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = MessageScreenType.Edit.screenRoute
    ) {
        composable(MessageScreenType.Edit.screenRoute) {
            MessageEditRoute(
                navHostController,
                myWritingTemplateData = myWritingTemplateData,
                onClickTextCopy = { onClickTextCopy(it) },
                onClickTextShare = { onClickTextShare(it) },
                onClickImageSave = { imageBitmap, writing ->
                    onClickImageSave(imageBitmap, writing)
                },
                onClickImageShare = { onClickImageShare(it) },
            )
        }
    }
}

sealed class MessageScreenType(val screenRoute: String) {
    object Detail : MessageScreenType("detail")
    object Edit : MessageScreenType("edit")
}