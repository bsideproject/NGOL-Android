package com.nugu.nuguollim.ui.my_page.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.nugu.my_page.component.MyListEmptyScreen
import com.nugu.nuguollim.common.data.model.template.MyWritingTemplateData
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.design_system.component.NuguTemplateItem

@Composable
internal fun ColumnScope.MyFavoriteTemplates(
    favoriteTemplates: LazyPagingItems<Template>,
    onFavorite: (Long, Boolean) -> Unit = { _, _ -> },
    onClickTemplate: (Template) -> Unit = {}
) {
    if (favoriteTemplates.itemCount > 0) {
        LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
            items(favoriteTemplates) { contentData ->
                if (contentData != null) {
                    var isFavorite by remember { mutableStateOf(contentData.favorite) }

                    NuguTemplateItem(
                        label = contentData.theme,
                        content = contentData.content,
                        isFavorite = isFavorite,
                        onClick = { onClickTemplate.invoke(contentData) },
                        onClickFavorite = {
                            isFavorite = !isFavorite
                            onFavorite.invoke(contentData.id, isFavorite)
                        },
                    )
                    Spacer(Modifier.padding(6.dp))
                }
            }
        }
    } else {
        MyListEmptyScreen()
    }
}

@Composable
internal fun ColumnScope.MyWritingTemplates(
    myWritingTemplates: LazyPagingItems<MyWritingTemplateData>
) {
    if (myWritingTemplates.itemCount > 0) {
        LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
            items(myWritingTemplates) { contentData ->
                if (contentData != null) {
                    NuguTemplateItem(
                        content = contentData.content,
                        onClick = {}
                    )
                    Spacer(Modifier.padding(6.dp))
                }
            }
        }
    } else {
        MyListEmptyScreen()
    }
}