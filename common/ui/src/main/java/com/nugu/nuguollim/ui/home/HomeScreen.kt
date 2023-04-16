package com.nugu.nuguollim.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nugu.nuguollim.design_system.component.NuguBottomNavigation
import com.nugu.nuguollim.design_system.component.NuguDropDownMenu
import com.nugu.nuguollim.design_system.component.NuguSearchTextField
import com.nugu.nuguollim.design_system.theme.Black
import com.nugu.nuguollim.design_system.theme.Primary500
import com.nugu.nuguollim.design_system.theme.pretendard
import com.nugu.nuguollim.ui.R

@Composable
fun HomeRoute(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        bottomBar = { NuguBottomNavigation(navController = navController) }
    ) { innerPadding ->

        HomeScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        HomeSearchBar()
        HomeSearchListMenu()
    }
}

@Composable
fun HomeSearchBar(
    modifier: Modifier = Modifier,
    text: String = "",
    onValueChange: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Primary500)
            .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo_top),
                contentDescription = "logo",
                modifier = Modifier
                    .width(62.dp)
                    .height(20.54.dp),
                tint = Color.White
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = "profile",
                modifier = Modifier
                    .width(19.65.dp)
                    .height(20.dp),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = stringResource(id = R.string.description_home),
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(26.dp))

        NuguSearchTextField(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun HomeSearchListMenu(
    modifier: Modifier = Modifier,
    onSortChanged: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.description_template_list),
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Black
            )
            NuguDropDownMenu(
                items = listOf("인기순", "최신순"),
                onSelectItem = { onSortChanged(it) }
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun HomeScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeScreen()
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun HomeSearchBarPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeSearchBar()
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun HomeSearchListMenuPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeSearchListMenu()
    }
}