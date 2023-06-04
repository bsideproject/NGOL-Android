package com.nugu.nuguollim.ui.my_page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nugu.my_detail.DeleteTemplateConfirmDialog
import com.nugu.my_page.component.MyPageNickName
import com.nugu.my_page.component.MyPageSettingDialog
import com.nugu.my_page.component.MyProfileSetting
import com.nugu.nuguollim.common.data.model.template.MyWritingTemplateData
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.design_system.component.NuguBottomNavigation
import com.nugu.nuguollim.design_system.component.NuguSwitch
import com.nugu.nuguollim.design_system.component.TermDialog
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.ui.my_page.component.MyFavoriteTemplates
import com.nugu.nuguollim.ui.my_page.component.MyNickNameChangeDialog
import com.nugu.nuguollim.ui.my_page.component.MyWritingTemplates
import com.nuguollim.data.state.ResultState

@Composable
fun MyPageRoute(
    navController: NavHostController,
    onSendMail: (String, String) -> Unit = { _, _ -> },
    onMoveLoginPage: () -> Unit = {},
    onClickTemplate: (Template) -> Unit = {},
    onClickMyWritingTemplate: (MyWritingTemplateData) -> Unit = {},
    onMoveDetailScreen: (MyWritingTemplateData) -> Unit = {},
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)

    Scaffold(bottomBar = { NuguBottomNavigation(navController) }) { innerPadding ->
        MyPageScreen(
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                bottom = innerPadding.calculateBottomPadding()
            ),
            onSendMail = onSendMail,
            onMoveLoginPage = onMoveLoginPage,
            onClickTemplate = onClickTemplate,
            onClickMyWritingTemplate = onClickMyWritingTemplate,
            onMoveDetailScreen = onMoveDetailScreen,
        )
    }
}

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    viewModel: MyPageViewModel = hiltViewModel(),
    onSendMail: (String, String) -> Unit = { _, _ -> },
    onMoveLoginPage: () -> Unit = {},
    onClickTemplate: (Template) -> Unit = {},
    onClickMyWritingTemplate: (MyWritingTemplateData) -> Unit = {},
    onMoveDetailScreen: (MyWritingTemplateData) -> Unit = {},
) {
    val myUserData by viewModel.myUserData.collectAsStateWithLifecycle()
    val termsData by viewModel.termsState.collectAsStateWithLifecycle()
    val myWritingTemplates = viewModel.myWritingTemplates.collectAsLazyPagingItems()
    val favoriteTemplates = viewModel.favoriteTemplates.collectAsLazyPagingItems()

    var showWriteScreen by remember { mutableStateOf(true) }
    var showNickNameChangeDialog by remember { mutableStateOf(false) }
    var showTermsDialog by remember { mutableStateOf(false) }
    var nickname by remember { mutableStateOf("") }
    var showDeleteTemplateConfirmDialog by remember { mutableStateOf(Pair(-1L, false)) }
    var showMyMessageDetailDialog by remember {
        mutableStateOf(
            Pair<MyWritingTemplateData?, Boolean>(
                null,
                false
            )
        )
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(lifecycle) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                myWritingTemplates.refresh()
            }
        }
        lifecycle.addObserver(lifecycleObserver)
        onDispose { lifecycle.removeObserver(lifecycleObserver) }
    }

    if (showNickNameChangeDialog) {
        MyNickNameChangeDialog(
            onDismissRequest = { showNickNameChangeDialog = false },
            onChangeNickName = { newNickname ->
                viewModel.setNickName(
                    nickname = newNickname,
                    success = {
                        nickname = newNickname
                        showNickNameChangeDialog = false
                    }
                )
            }
        )
    }

    if (showDeleteTemplateConfirmDialog.second) {
        DeleteTemplateConfirmDialog(
            id = showDeleteTemplateConfirmDialog.first,
            onDeleteTemplate = { id ->
                viewModel.removeTemplate(
                    id = id,
                    success = myWritingTemplates::refresh,
                    fail = { it.printStackTrace() }
                )
                showDeleteTemplateConfirmDialog = Pair(-1L, false)
            },
            onDismissRequest = { showDeleteTemplateConfirmDialog = Pair(-1L, false) }
        )
    }

    if (showMyMessageDetailDialog.second) {
//        MyMessageDetailDialog(
//            myWritingTemplateData = showMyMessageDetailDialog.first!!,
//            onMovePageMessageEdit = { myWritingTemplate ->
//                showMyMessageDetailDialog = Pair(null, false)
//                onClickMyWritingTemplate.invoke(myWritingTemplate)
//            },
//            onDeleteTemplate = { id ->
//                viewModel.removeTemplate(
//                    id = id,
//                    success = myWritingTemplates::refresh,
//                    fail = { it.printStackTrace() }
//                )
//                showMyMessageDetailDialog = Pair(null, false)
//            },
//            onDismissRequest = { showMyMessageDetailDialog = Pair(null, false) },
//            onClickImageSave = onClickImageSave,
//            onClickImageShare = onClickImageShare
//        )
    }

    if (termsData is ResultState.Success && showTermsDialog) {
        TermDialog(
            link = termsData.successData.first().link,
            onDismissRequest = { showTermsDialog = false }
        )
    }

    if (myUserData is ResultState.Success) {
        LaunchedEffect(myUserData) {
            nickname = myUserData.successData.nickname
        }
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MyPageNickNameSettingScreen(
                nickname = nickname,
                providerType = myUserData.successData.providerType,
                onNicknameChange = { showNickNameChangeDialog = true },
                onTermsPage = { termsTitle ->
                    viewModel.getTerms(termsTitle)
                    showTermsDialog = true
                },
                onSendMail = { onSendMail.invoke(nickname, it) },
                onLogout = {
                    viewModel.clearAuthInfo { onMoveLoginPage.invoke() }
                },
                onUnRegister = {
                    viewModel.unregister(
                        success = onMoveLoginPage,
                        fail = { it.printStackTrace() }
                    )
                },
            )
            NuguSwitch(
                text1 = "작성한 메세지",
                text2 = "즐겨찾기 템플릿",
                onChecked = { showWriteScreen = it }
            )

            if (showWriteScreen) {
                MyWritingTemplates(
                    myWritingTemplates = myWritingTemplates,
                    onRemoveItem = { showDeleteTemplateConfirmDialog = Pair(it, true) },
                    onMoveDetailScreen = onMoveDetailScreen,
                    onMoveEditScreen = { onClickMyWritingTemplate.invoke(it) }
                )
            } else {
                MyFavoriteTemplates(
                    favoriteTemplates = favoriteTemplates,
                    onClickTemplate = onClickTemplate,
                    onFavorite = { id, isFavorite ->
                        if (isFavorite) {
                            viewModel.addFavorite(id)
                        } else {
                            viewModel.removeFavorite(id)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun MyPageNickNameSettingScreen(
    modifier: Modifier = Modifier,
    nickname: String,
    providerType: String,
    onTermsPage: (String) -> Unit = {},
    onNicknameChange: () -> Unit = {},
    onSendMail: (String) -> Unit = {},
    onLogout: () -> Unit = {},
    onUnRegister: () -> Unit = {}
) {
    var showSettingDialog by remember { mutableStateOf(false) }

    if (showSettingDialog) {
        MyPageSettingDialog(
            providerType = providerType,
            onTermsPage = onTermsPage,
            onDismissRequest = { showSettingDialog = false },
            onSendMail = onSendMail,
            onLogout = onLogout,
            onUnRegister = onUnRegister
        )
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyProfileSetting(
            modifier = Modifier
                .padding(vertical = 18.dp, horizontal = 20.dp)
                .align(Alignment.End)
                .clickable { showSettingDialog = true }
        )
        MyPageNickName(
            nickname = nickname,
            modifier = Modifier.fillMaxWidth(),
            onNicknameChange = onNicknameChange
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    NuguollimTheme {
    }
}