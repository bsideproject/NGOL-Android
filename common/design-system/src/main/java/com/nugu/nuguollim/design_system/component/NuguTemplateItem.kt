package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.nuguollim.design_system.R
import com.nugu.nuguollim.design_system.theme.Black
import com.nugu.nuguollim.design_system.theme.pretendard

@Composable
fun NuguTemplateItem(
    modifier: Modifier = Modifier,
    label: String = stringResource(id = R.string.home_template_item_label_example),
    content: String = stringResource(id = R.string.home_template_item_content_example),
    isFavorite: Boolean = false,
    onClick: () -> Unit = {},
    onClickFavorite: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 1.dp, color = Color(0xFFD0D0D0))
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 12.dp, bottom = 16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Chip(
                    onClick = { },
                    content = {
                        Text(
                            text = label,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = Black
                        )
                    },
                    colors = ChipDefaults.outlinedChipColors(
                        backgroundColor = Color.White,
                        contentColor = Black
                    ),
                    border = BorderStroke(1.dp, Black)
                )
                NuguFavorite(isFavorite = isFavorite, onClick = onClickFavorite)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = content,
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Black
            )
        }
    }
}


@Composable
fun NuguTemplateItem(
    modifier: Modifier = Modifier,
    target: AnnotatedString,
    label: AnnotatedString,
    content: AnnotatedString,
    isFavorite: Boolean = false,
    onClick: () -> Unit = {},
    onClickFavorite: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 1.dp, color = Color(0xFFD0D0D0))
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 12.dp, bottom = 16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Chip(
                    onClick = { },
                    content = {
                        Text(
                            text = target,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = Black
                        )
                    },
                    colors = ChipDefaults.outlinedChipColors(
                        backgroundColor = Color.White,
                        contentColor = Black
                    ),
                    border = BorderStroke(1.dp, Black)
                )
                Spacer(modifier = Modifier.padding(start = 4.dp))
                Chip(
                    onClick = { },
                    content = {
                        Text(
                            text = label,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = Black
                        )
                    },
                    colors = ChipDefaults.outlinedChipColors(
                        backgroundColor = Color.White,
                        contentColor = Black
                    ),
                    border = BorderStroke(1.dp, Black)
                )
                Spacer(modifier = Modifier.weight(1f))
                NuguFavorite(isFavorite = isFavorite, onClick = onClickFavorite)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = content,
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Black
            )
        }
    }
}

@Composable
private fun NuguFavorite(
    isFavorite: Boolean,
    onClick: () -> Unit
) {
    val resource = if (isFavorite) R.drawable.fi_sr_bookmark else R.drawable.fi_rr_bookmark

    IconButton(onClick = onClick) {
        Image(
            painter = painterResource(resource),
            contentDescription = "favorite"
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NuguTemplateItemPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NuguTemplateItem(
        )
    }
}