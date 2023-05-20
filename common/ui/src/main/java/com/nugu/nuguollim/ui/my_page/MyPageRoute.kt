package com.nugu.nuguollim.ui.my_page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.nugu.my_page.component.MyPageNickName
import com.nugu.my_page.component.MyPageSettingDialog
import com.nugu.my_page.component.MyProfileSetting
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.design_system.component.NuguBottomNavigation
import com.nugu.nuguollim.design_system.component.NuguSwitch
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
) {
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
) {
    val myUserData by viewModel.myUserData.collectAsStateWithLifecycle()
    val myWritingTemplates = viewModel.myWritingTemplates.collectAsLazyPagingItems()
    val favoriteTemplates = viewModel.favoriteTemplates.collectAsLazyPagingItems()

    var showWriteScreen by remember { mutableStateOf(true) }
    var showNickNameChangeDialog by remember { mutableStateOf(false) }
    var nickname by remember { mutableStateOf("") }

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
                MyWritingTemplates(myWritingTemplates)
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
    onNicknameChange: () -> Unit = {},
    onSendMail: (String) -> Unit = {},
    onLogout: () -> Unit = {},
    onUnRegister: () -> Unit = {}
) {
    var showSettingDialog by remember { mutableStateOf(false) }

    if (showSettingDialog) {
        MyPageSettingDialog(
            providerType = providerType,
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