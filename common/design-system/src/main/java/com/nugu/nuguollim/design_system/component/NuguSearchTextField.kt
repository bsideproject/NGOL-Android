package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.nuguollim.design_system.R
import com.nugu.nuguollim.design_system.theme.Gray300
import com.nugu.nuguollim.design_system.theme.Gray400
import com.nugu.nuguollim.design_system.theme.pretendard

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NuguSearchTextField(
    modifier: Modifier = Modifier,
    searchText: String = "",
    onSearchText: (String) -> Unit = {},
    onValueChange: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    val leadingPainter = painterResource(id = R.drawable.ic_search)

    Column(
        modifier = modifier
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .focusRequester(focusRequester),
            value = searchText,
            onValueChange = onValueChange,
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = pretendard,
                color = Color.Black
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Gray300,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(17.dp),
                        painter = leadingPainter,
                        contentDescription = "search",
                    )
                    Spacer(modifier = Modifier.width(6.dp))

                    if (searchText.isEmpty()) {
                        Text(
                            text = "검색어를 입력해주세요.",
                            fontFamily = pretendard,
                            fontWeight = FontWeight.Normal,
                            color = Gray400
                        )
                    }
                    innerTextField()
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchText(searchText)

                    keyboardController?.hide()
                    focusRequester.freeFocus()
                }
            )
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NuguSearchTextFieldPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NuguSearchTextField()
    }
}