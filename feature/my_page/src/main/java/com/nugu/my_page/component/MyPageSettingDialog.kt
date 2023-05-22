package com.nugu.my_page.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nugu.my_page.R
import com.nugu.nuguollim.design_system.component.NuguToolbar
import com.nugu.nuguollim.design_system.component.NuguToolbarTitle
import com.nugu.nuguollim.design_system.component.PretendardText
import com.nugu.nuguollim.design_system.theme.Black
import com.nugu.nuguollim.design_system.theme.Gray100
import com.nugu.nuguollim.design_system.theme.NuguIcons
import com.nugu.nuguollim.design_system.ui_tools.dimClear
import com.nugu.ui_core.SingleClickEventListener
import com.nugu.ui_core.singleClick
import kotlinx.coroutines.launch

@Composable
fun MyPageSettingDialog(
    providerType: String,
    onTermsPage: (String) -> Unit = {},
    onDismissRequest: () -> Unit = {},
    onSendMail: (String) -> Unit = {},
    onLogout: () -> Unit = {},
    onUnRegister: () -> Unit = {},
) {
    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = onDismissRequest
    ) {
        val sheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            confirmValueChange = { it == ModalBottomSheetValue.Hidden }
        )
        val coroutineScope = rememberCoroutineScope()
        var showLogoutDialog by remember { mutableStateOf(false) }

        LocalView.current.dimClear()

        if (showLogoutDialog) {
            LogoutDialog(
                onDismissRequest = { showLogoutDialog = false },
                onLogout = onLogout
            )
        }

        UnRegisterBottomSheet(
            sheetState = sheetState,
            onUnRegister = onUnRegister
        ) {
            Scaffold(
                topBar = {
                    NuguToolbar(
                        navigation = {
                            IconButton(onClick = onDismissRequest) {
                                NuguIcons.BackArrow
                            }
                        },
                        title = { NuguToolbarTitle("설정") }
                    )
                }
            ) { paddingValues ->
                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(Gray100)
                ) {
                    MyPageSettingScreen(
                        modifier = Modifier.padding(paddingValues),
                        onTermsPage = onTermsPage,
                        onSendMail = onSendMail,
                        onLogoutClick = { showLogoutDialog = true },
                        onUnRegisterClick = {
                            coroutineScope.launch { sheetState.show() }
                        }
                    )
                    Row(
                        modifier = Modifier.padding(start = 28.dp, top = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val (iconResource, platform) = when (providerType) {
                            "KAKAO" -> Pair(R.drawable.icon_kakao, "카카오")
                            "NAVER" -> Pair(R.drawable.icon_naver, "네이버")
                            else -> Pair(R.drawable.icon_google, "구글")
                        }
                        Image(
                            painter = painterResource(iconResource),
                            contentDescription = "platform"
                        )
                        PretendardText(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "$platform 계정 회원",
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MyPageSettingScreen(
    modifier: Modifier = Modifier,
    onTermsPage: (String) -> Unit = {},
    onSendMail: (String) -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onUnRegisterClick: () -> Unit = {}
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 30.dp)
        ) {
            singleClick { singleClickListener ->
                MyPageMenu(
                    text = "이용 약관",
                    singleClickListener = singleClickListener,
                    onClick = { onTermsPage.invoke("서비스 이용약관") }
                )
                MyPageMenu(
                    text = "개인정보처리방침",
                    singleClickListener = singleClickListener,
                    onClick = { onTermsPage.invoke("개인정보 처리방침") }
                )
                MyPageMenu(
                    text = "이용 문의",
                    singleClickListener = singleClickListener,
                    onClick = { onSendMail.invoke("이용 문의") }
                )
                MyPageMenu(
                    text = "템플릿 요청/제보",
                    singleClickListener = singleClickListener,
                    onClick = { onSendMail.invoke("템플릿 요청/제보") }
                )
                MyPageMenu(
                    text = "로그아웃",
                    singleClickListener = singleClickListener,
                    onClick = onLogoutClick
                )
                MyPageMenu(
                    text = "탈퇴하기",
                    singleClickListener = singleClickListener,
                    onClick = onUnRegisterClick
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp)
                        .background(Color.White)
                )
            }
        }
    }
}

@Composable
private fun MyPageMenu(
    modifier: Modifier = Modifier,
    text: String,
    singleClickListener: SingleClickEventListener,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { singleClickListener.onClick(onClick) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PretendardText(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Black
        )
        Image(
            painter = painterResource(R.drawable.image_right_arrow),
            contentDescription = "Move Page"
        )
    }
}