package com.nugu.my_page.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.my_page.R
import com.nugu.nuguollim.design_system.component.NuguFillButton
import com.nugu.nuguollim.design_system.component.PretendardText
import com.nugu.nuguollim.design_system.theme.Black
import com.nugu.nuguollim.design_system.theme.Gray200
import com.nugu.nuguollim.design_system.theme.Gray600
import com.nugu.nuguollim.design_system.theme.Gray700
import com.nugu.nuguollim.design_system.theme.Gray800
import kotlinx.coroutines.launch

@Composable
fun UnRegisterBottomSheet(
    sheetState: ModalBottomSheetState,
    onUnRegister: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val dismiss: () -> Unit = {
        coroutineScope.launch { sheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
        sheetElevation = 0.dp,
        sheetContent = {
            if (sheetState.currentValue == ModalBottomSheetValue.Expanded) {
                UnRegisterScreen(
                    onUnRegister = onUnRegister,
                    onDismiss = dismiss
                )
            }
            BackHandler(
                enabled = sheetState.isVisible,
                onBack = dismiss
            )
        },
        content = content
    )
}

@Composable
private fun UnRegisterScreen(
    onUnRegister: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.padding(top = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            PretendardText(
                text = "탈퇴하기",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Black,
            )
        }
        Image(
            modifier = Modifier.padding(top = 4.dp),
            painter = painterResource(R.drawable.image_unregister),
            contentDescription = "unregister"
        )
        PretendardText(
            modifier = Modifier.padding(top = 16.dp),
            text = "정말 탈퇴하시겠어요?",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Gray800
        )
        PretendardText(
            modifier = Modifier.padding(top = 8.dp),
            text = "회원 탈퇴 시 작성한 기록은 모두 서비스 정책에 따라 삭제돼요.",
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Gray600
        )
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
        ) {
            NuguFillButton(
                modifier = Modifier
                    .height(48.dp)
                    .weight(1f),
                activeButtonColor = Gray200,
                activeTextColor = Gray700,
                text = "취소하기",
                onClick = onDismiss
            )
            Spacer(modifier = Modifier.padding(start = 6.dp))
            NuguFillButton(
                modifier = Modifier
                    .height(48.dp)
                    .weight(1f),
                text = "탈퇴하기",
                onClick = onUnRegister
            )
        }
        Spacer(modifier = Modifier.padding(top = 26.dp))
    }
}