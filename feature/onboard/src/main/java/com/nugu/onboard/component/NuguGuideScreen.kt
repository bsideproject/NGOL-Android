package com.nugu.onboard.component

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.nugu.nuguollim.design_system.component.NuguFillButton
import com.nugu.nuguollim.design_system.component.PretendardText
import com.nugu.nuguollim.design_system.theme.Black
import com.nugu.nuguollim.design_system.theme.Gray200
import com.nugu.nuguollim.design_system.theme.Gray500
import com.nugu.nuguollim.design_system.theme.Gray700
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.design_system.theme.Primary500
import com.nugu.onboard.R
import kotlinx.coroutines.launch

private const val MAX_PAGE = 3

@Composable
fun NuguGuideScreen(
    modifier: Modifier = Modifier,
    onStart: () -> Unit = {},
) {
    val pageCount = MAX_PAGE
    val pagerState = rememberPagerState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(
            pageCount = pageCount,
            state = pagerState
        ) { GuidePageScreen(it) }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = pageCount,
            inactiveColor = Gray200,
            activeColor = Primary500,
            modifier = Modifier
                .padding(top = 30.dp)
        )

        if (pagerState.currentPage == MAX_PAGE - 1) {
            NuguFillButton(
                text = "시작하기",
                onClick = onStart,
                modifier = Modifier
                    .padding(top = 58.dp)
                    .padding(horizontal = 20.dp),
            )
        } else {
            val coroutineScope = rememberCoroutineScope()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 76.dp)
                    .padding(horizontal = 28.dp)
            ) {
                PretendardText(
                    text = "건너뛰기",
                    color = Gray500,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { onStart.invoke() }
                )
                Spacer(modifier = Modifier.weight(1f))
                PretendardText(
                    text = "다음",
                    color = Primary500,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        coroutineScope.launch {
                            pagerState.scrollToPage(
                                page = pagerState.currentPage + 1,
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun GuidePageScreen(page: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (page) {
            0 -> {
                GuidePage(
                    painter = painterResource(R.drawable.onboard_1),
                    contentDescription = "onboard_1",
                    title = "어렵고 난감한\n상황도 괜찮아요",
                    description = "누구올림 템플릿이 도와줄게요",
                )
            }
            1 -> {
                GuidePage(
                    painter = painterResource(R.drawable.onboard_2),
                    contentDescription = "onboard_2",
                    title = "다양하고 재미있는\n테마와 함께",
                    description = "템플릿을 꾸미고 공유까지 가능해요",
                )
            }
            2 -> {
                GuidePage(
                    painter = painterResource(R.drawable.onboard_3),
                    contentDescription = "onboard_3",
                    title = "찾는게 없어도\n걱정하지 마세요",
                    description = "누구올림과 올리머들이 당신을 도와드릴게요",
                )
            }
        }
    }
}

@Composable
private fun GuidePage(
    painter: Painter,
    contentDescription: String,
    title: String,
    description: String
) {
    Box(
        modifier = Modifier.size(257.dp, 251.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(painter = painter, contentDescription = contentDescription)
    }
    PretendardText(
        text = title,
        color = Black,
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp,
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(top = 53.dp)
    )
    PretendardText(
        text = description,
        color = Gray700,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Preview
@Composable
private fun NuguGuidePreview() {
    NuguollimTheme {
        NuguGuideScreen()
    }
}