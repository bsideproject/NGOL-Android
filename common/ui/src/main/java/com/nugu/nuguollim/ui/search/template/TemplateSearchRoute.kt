package com.nugu.nuguollim.ui.search.template

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.design_system.component.NuguLoadingProgressDialog
import com.nugu.search.nav.navigateToTarget
import com.nugu.search.ui.TemplateSearchScreen
import com.nuguollim.data.state.ResultState
import kotlinx.coroutines.flow.Flow

@Composable
fun TemplateSearchRoute(
    navController: NavHostController,
    viewModel: TemplateSearchViewModel = hiltViewModel(),
    onClickTemplate: (Template) -> Unit = {}
) {
    val templates by viewModel.templatesParams.collectAsStateWithLifecycle(null)

    when (templates) {
        is ResultState.Error ->
            (templates as ResultState.Error).errorData?.printStackTrace()
        is ResultState.Loading -> NuguLoadingProgressDialog()
        is ResultState.Success -> TemplateSearchScreen(
            keyword = viewModel.keyword,
            templatesData = (templates as ResultState.Success<Flow<PagingData<Template>>>).successData,
            onSortChanged = { viewModel.updateSort(it.sortText) },
            onFavorite = { id, isFavorite ->
                if (isFavorite) {
                    viewModel.addFavorite(id)
                } else {
                    viewModel.removeFavorite(id)
                }
            },
            onNavigateToSearchHome = { navController.navigateToTarget() },
            onClickTemplate = onClickTemplate
        )
        null -> Unit
    }
}