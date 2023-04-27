package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.nuguollim.design_system.R
import com.nugu.nuguollim.design_system.theme.Gray300
import com.nugu.nuguollim.design_system.theme.Gray700
import com.nugu.nuguollim.design_system.theme.Gray800
import com.nugu.nuguollim.design_system.theme.pretendard

@Composable
fun TermsCheckBoxGroup(
    modifier: Modifier = Modifier,
    checkUse: Boolean = false,
    checkPrivate: Boolean = false,
    checkMarketing: Boolean = false,
    onCheckedUse: (Boolean) -> Unit = {},
    onCheckedPrivate: (Boolean) -> Unit = {},
    onCheckedMarketing: (Boolean) -> Unit = {},
) {
    var checkAll by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        TermsCheckBox(
            modifier = Modifier.fillMaxWidth(),
            text = "전체 동의하기",
            isTotal = true,
            checked = checkAll,
            onChecked = {
                checkAll = it
                onCheckedUse(it)
                onCheckedPrivate(it)
                onCheckedMarketing(it)
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        TermsCheckBox(
            modifier = Modifier.fillMaxWidth(),
            text = "[필수] 이용약관 동의",
            checked = checkUse,
            onChecked = { onCheckedUse(it) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TermsCheckBox(
            modifier = Modifier.fillMaxWidth(),
            text = "[필수] 개인정보 처리방침",
            checked = checkPrivate,
            onChecked = { onCheckedPrivate(it) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TermsCheckBox(
            modifier = Modifier.fillMaxWidth(),
            text = "[선택] 마케팅 정보 수신 동의",
            checked = checkMarketing,
            onChecked = { onCheckedMarketing(it) }
        )
    }
}

@Composable
fun TermsCheckBox(
    modifier: Modifier = Modifier,
    text: String = "",
    isTotal: Boolean = false,
    checked: Boolean = false,
    onChecked: (Boolean) -> Unit = {},
    onClickMore: () -> Unit = {}
) {
    val painterCheck = painterResource(id = R.drawable.ic_checkbox_check)
    val painterDefault = painterResource(id = R.drawable.ic_checkbox_default)
    val painterArrow = painterResource(id = R.drawable.ic_icon_right_arrow)

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(16.dp)
                .clickable {
                    onChecked(checked.not())
                },
            contentDescription = "check box",
            contentScale = ContentScale.Fit,
            painter = if (checked) painterCheck else painterDefault
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            color = if (isTotal) Gray800 else Gray700,
            fontFamily = pretendard,
            fontSize = if (isTotal) 14.sp else 13.sp,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Medium
        )
        if (isTotal.not()) {
            Image(
                modifier = Modifier.clickable { onClickMore() },
                contentDescription = "more terms",
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Gray300),
                painter = painterArrow
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun TermsCheckBoxPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TermsCheckBoxGroup()
    }
}