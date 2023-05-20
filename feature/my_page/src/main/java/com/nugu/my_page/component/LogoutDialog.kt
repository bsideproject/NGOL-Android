package com.nugu.my_page.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.nugu.nuguollim.design_system.component.NuguFillButton
import com.nugu.nuguollim.design_system.component.PretendardText
import com.nugu.nuguollim.design_system.theme.Black
import com.nugu.nuguollim.design_system.theme.Gray200
import com.nugu.nuguollim.design_system.theme.Gray600
import com.nugu.nuguollim.design_system.theme.Gray700

@Composable
fun LogoutDialog(
    onDismissRequest: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    Dialog(onDismissRequest) {
        Column(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp)
                .padding(top = 20.dp, bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PretendardText(
                text = "로그아웃",
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.SemiBold
            )
            PretendardText(
                modifier = Modifier.padding(top = 12.dp),
                text = "로그아웃 하시겠어요?",
                fontSize = 14.sp,
                color = Gray600,
                fontWeight = FontWeight.Normal
            )
            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            ) {
                NuguFillButton(
                    modifier = Modifier.weight(1f),
                    activeButtonColor = Gray200,
                    activeTextColor = Gray700,
                    text = "취소",
                    onClick = onDismissRequest
                )
                Spacer(modifier = Modifier.padding(start = 6.dp))
                NuguFillButton(
                    modifier = Modifier.weight(1f),
                    text = "확인",
                    onClick = onLogout
                )
            }
        }
    }
}