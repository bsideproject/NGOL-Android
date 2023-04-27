package com.nugu.nuguollim.ui.search.target

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.nugu.search.nav.navigateToTheme
import com.nugu.search.ui.TargetSearchScreen
import com.nuguollim.data.state.ResultState

@Composable
fun TargetChoiceScreen(
    navController: NavHostController,
    viewModel: TargetSearchViewModel = hiltViewModel()
) {
    val templateTargetListState by viewModel.templateTargetListState.collectAsStateWithLifecycle()

    when (templateTargetListState) {
        is ResultState.Error -> templateTargetListState.errorData?.printStackTrace()
        is ResultState.Loading -> Unit
        is ResultState.Success -> TargetSearchScreen(
            templateTargets = templateTargetListState.successData,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
        ) { navController.navigateToTheme(it) }
    }
}