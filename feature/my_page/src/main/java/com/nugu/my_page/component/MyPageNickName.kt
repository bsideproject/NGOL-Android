package com.nugu.my_page.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.my_page.R
import com.nugu.nuguollim.design_system.component.PretendardText
import com.nugu.nuguollim.design_system.theme.Black
import com.nugu.nuguollim.design_system.theme.Gray100
import com.nugu.nuguollim.design_system.theme.Gray200
import com.nugu.nuguollim.design_system.theme.Gray600
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.design_system.theme.Primary400

@Composable
fun MyPageNickName(
    modifier: Modifier = Modifier,
    nickname: String,
    onNicknameChange: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Primary400,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(
                vertical = 22.dp,
                horizontal = 11.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PretendardText(
            text = nickname,
            fontSize = 14.sp,
            color = Black,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 13.dp)
        )

        Button(
            onClick = onNicknameChange,
            colors = buttonColors,
            modifier = Modifier.size(71.dp, 26.dp),
            contentPadding = PaddingValues(0.dp),
            elevation = null,
            border = BorderStroke(1.dp, Gray200)
        ) {
            PretendardText(
                text = "프로필 수정",
                color = Gray600,
                fontSize = 12.sp
            )
        }
    }
}

@Stable
@Composable
fun MyProfileSetting(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.image_setting),
        contentDescription = "Setting",
        modifier = modifier
    )
}

private val buttonColors
    @Stable
    @Composable get() = ButtonDefaults.buttonColors(
        backgroundColor = Gray100
    )

@Preview
@Composable
private fun MyPageNickNamePreview() {
    NuguollimTheme {
        MyPageNickName(nickname = "ㅎㅇ")
    }
}