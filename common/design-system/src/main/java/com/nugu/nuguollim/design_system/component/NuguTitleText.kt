package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.nuguollim.design_system.R
import com.nugu.nuguollim.design_system.theme.Black
import com.nugu.nuguollim.design_system.theme.Primary500
import com.nugu.nuguollim.design_system.theme.pretendard

@Composable
fun NuguMessageTitle(
    modifier: Modifier = Modifier,
    textTarget: String,
    textTheme: String,
) {
    val strongStyle = SpanStyle(
        color = Primary500,
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
    )
    val normalStyle = SpanStyle(
        color = Black,
        fontFamily = pretendard,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    )
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopStart),
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(strongStyle) { append(textTarget) }
                    withStyle(normalStyle) { append("에게") }
                }
            )
            Text(
                buildAnnotatedString {
                    withStyle(strongStyle) { append(textTheme) }
                    withStyle(normalStyle) { append(" 편지를 올려봐요!") }
                }
            )
        }

        Column(
            modifier = Modifier.align(Alignment.CenterStart),
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Image(
                painter = painterResource(id = R.drawable.img_underscore),
                contentDescription = "underscore"
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NuguMessageTitlePreview() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NuguMessageTitle(textTarget = "교수님", textTheme = "성적문의")
    }
}