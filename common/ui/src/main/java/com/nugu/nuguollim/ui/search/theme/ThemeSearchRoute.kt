package com.nugu.nuguollim.ui.search.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.design_system.component.NuguLoadingProgressDialog
import com.nugu.search.ui.ThemeSearchScreen
import com.nuguollim.data.state.ResultState
import kotlinx.coroutines.flow.Flow

@Composable
fun ThemeSearchRoute(
    navController: NavHostController,
    viewModel: ThemeSearchViewModel = hiltViewModel()
) {
    val templates by viewModel.templatesParams.collectAsStateWithLifecycle(null)

    when (templates) {
        is ResultState.Error -> {
            (templates as ResultState.Error).errorData?.printStackTrace()
        }
        is ResultState.Loading -> NuguLoadingProgressDialog()
        is ResultState.Success -> ThemeSearchScreen(
            targetData = viewModel.targetData,
            templatesData = (templates as ResultState.Success<Flow<PagingData<Template>>>).successData,
            onSortChanged = { viewModel.updateSort(it.sortText) },
            onThemeSelectChanged = { viewModel.updateTheme(it) },
            onFavorite = { id, isFavorite ->
                if (isFavorite) {
                    viewModel.addFavorite(id)
                } else {
                    viewModel.removeFavorite(id)
                }
            }
        )
        null -> Unit
    }
}