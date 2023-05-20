package com.nugu.nuguollim.ui.my_page.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nugu.nuguollim.design_system.component.NuguFillButton
import com.nugu.nuguollim.design_system.component.NuguNameTextField
import com.nugu.nuguollim.design_system.component.NuguToolbar
import com.nugu.nuguollim.design_system.component.NuguToolbarTitle
import com.nugu.nuguollim.design_system.component.PretendardText
import com.nugu.nuguollim.design_system.theme.Black
import com.nugu.nuguollim.design_system.theme.NuguIcons
import com.nugu.nuguollim.design_system.ui_tools.dimClear

@Composable
fun MyNickNameChangeDialog(
    onDismissRequest: () -> Unit = {},
    onChangeNickName: (String) -> Unit = {}
) {
    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = onDismissRequest
    ) {
        LocalView.current.dimClear()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                NuguToolbar(
                    navigation = {
                        IconButton(onClick = onDismissRequest) {
                            NuguIcons.BackArrow
                        }
                    },
                    title = { NuguToolbarTitle("프로필 수정") }
                )
            }
        ) { paddingValues ->
            ChangeNickNameScreen(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 9.dp)
            ) { onChangeNickName.invoke(it) }
        }
    }
}

@Composable
private fun ChangeNickNameScreen(
    modifier: Modifier = Modifier,
    onChangeNickName: (String) -> Unit = {}
) {
    var name by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        Spacer(Modifier.weight(1f))
        PretendardText(
            text = "편지에 쓸 내 이름,\n혹은 닉네임을 변경해주세요.",
            fontSize = 26.sp,
            color = Black,
            fontWeight = FontWeight.Medium
        )
        NuguNameTextField(
            modifier = Modifier.padding(top = 24.dp),
            name = name,
            onValueChange = { name = it }
        )
        Spacer(Modifier.weight(1f))
        NuguFillButton(
            modifier = Modifier.padding(20.dp),
            text = "변경하기",
            onClick = { onChangeNickName.invoke(name) }
        )
    }
}