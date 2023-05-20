package com.nugu.my_page.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.nugu.my_page.R

@Composable
fun ColumnScope.MyListEmptyScreen() {
    Spacer(Modifier.weight(1f))
    Image(painter = painterResource(R.drawable.empty_my_list), contentDescription = "empty my list")
    Spacer(Modifier.weight(1f))
}