package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.nuguollim.design_system.R
import com.nugu.nuguollim.design_system.theme.Gray800
import com.nugu.nuguollim.design_system.theme.pretendard

@Composable
fun NuguDropDownMenu(
    items: List<String> = listOf(),
    onSelectItem: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(items.firstOrNull() ?: "") }
    var dropDownWidth by remember { mutableStateOf(0) }
    val expandedIconId =
        if (expanded) R.drawable.ic_drop_down_less else R.drawable.ic_drop_down_more

    Column {
        Row(
            modifier = Modifier
                .clickable { expanded = expanded.not() }
                .onSizeChanged { dropDownWidth = it.width }
                .padding(start = 12.dp, top = 3.5.dp, bottom = 3.5.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selected,
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Gray800
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = expandedIconId),
                contentDescription = "sort expanded",
                tint = Gray800
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { dropDownWidth.toDp() })
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        selected = item
                        onSelectItem(item)
                        expanded = false
                    }
                ) {
                    Text(
                        text = item,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Gray800
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NuguDropDownMenuPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NuguDropDownMenu(
            items = listOf("인기순", "최신순")
        )
    }
}