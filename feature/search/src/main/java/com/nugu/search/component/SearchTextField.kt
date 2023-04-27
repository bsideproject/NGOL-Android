package com.nugu.search.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.nuguollim.design_system.R
import com.nugu.nuguollim.design_system.component.NuguTextField
import com.nugu.nuguollim.design_system.component.PlaceHolderStyle
import com.nugu.nuguollim.design_system.theme.Gray300
import com.nugu.nuguollim.design_system.theme.Gray400
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.nuguollim.design_system.theme.Primary500

@Composable
fun SearchTextField(
    value: String,
    isFocused: Boolean = false,
    onValueChange: (String) -> Unit = {},
    placeHolder: String = "",
    onClear: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val placeHolderStyle = PlaceHolderStyle(
        placeHolder = placeHolder,
        fontSize = 14.sp,
        color = Gray400,
        modifier = Modifier.padding(start = 6.dp)
    )
    val borderStroke = if (isFocused) {
        BorderStroke(1.5.dp, Primary500)
    } else {
        BorderStroke(1.dp, Gray300)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .border(borderStroke, RoundedCornerShape(5.dp))
            .padding(start = 10.dp)
    ) {
        SearchImage()

        Box(modifier = Modifier.weight(1f)) {
            NuguTextField(
                value = value,
                onValueChange = onValueChange,
                placeHolderStyle = placeHolderStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp)
            )
        }

        if (value.isNotEmpty()) {
            ClearButton(onClick = onClear)
        }
    }
}

@Composable
private fun SearchImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.icon_search),
        modifier = modifier,
        contentDescription = "검색"
    )
}

@Composable
private fun ClearButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Image(
            painter = painterResource(R.drawable.icon_clear),
            modifier = modifier,
            contentDescription = "지우기",
        )
    }
}

@Preview
@Composable
private fun SearchIconsPreview() {
    NuguollimTheme(false) {
        Row {
            SearchImage()
            ClearButton {}
        }
    }
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    NuguollimTheme(false) {
        SearchTextField(
            value = "",
            placeHolder = "검색어를 입력해주세요.",
            modifier = Modifier.size(320.dp, 40.dp),
        )
    }
}