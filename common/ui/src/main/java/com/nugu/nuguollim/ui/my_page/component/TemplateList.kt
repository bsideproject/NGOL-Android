package com.nugu.nuguollim.ui.my_page.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissValue
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.nugu.my_page.component.MyListEmptyScreen
import com.nugu.nuguollim.common.data.model.template.MyWritingTemplateData
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.design_system.component.NuguTemplateItem
import com.nugu.nuguollim.design_system.component.PretendardText
import com.nugu.nuguollim.design_system.theme.Primary500

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
    myWritingTemplates: LazyPagingItems<MyWritingTemplateData>,
    onRemoveItem: (Long) -> Unit = {},
    onMoveEditScreen: (MyWritingTemplateData) -> Unit = {},
    onMoveDetailScreen: (MyWritingTemplateData) -> Unit = {},
) {
    if (myWritingTemplates.itemCount > 0) {
        LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
            items(myWritingTemplates) { contentData ->
                if (contentData != null) {
                    val currentItem by rememberUpdatedState(contentData)
                    val dismissState = rememberDismissState(
                        confirmStateChange = { dismissValue ->
                            when (dismissValue) {
                                DismissValue.Default -> Unit
                                DismissValue.DismissedToEnd -> onMoveEditScreen.invoke(currentItem)
                                DismissValue.DismissedToStart -> onRemoveItem.invoke(currentItem.id)
                            }
                            false
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        dismissThresholds = { FractionalThreshold(0.3f) },
                        background = {
                            when (dismissState.targetValue) {
                                DismissValue.Default -> Unit
                                DismissValue.DismissedToEnd -> Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Primary500, RoundedCornerShape(10.dp)),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    PretendardText(
                                        text = "수정",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White,
                                        modifier = Modifier.padding(start = 32.dp)
                                    )
                                }
                                DismissValue.DismissedToStart -> Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color(0xFFFF4E43), RoundedCornerShape(10.dp)),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    PretendardText(
                                        text = "삭제",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White,
                                        modifier = Modifier.padding(end = 32.dp)
                                    )
                                }
                            }
                        },
                        dismissContent = {
                            NuguTemplateItem(
                                target = contentData.target,
                                theme = contentData.theme,
                                content = contentData.content,
                                onClick = { onMoveDetailScreen.invoke(contentData) }
                            )
                        }
                    )
                    Spacer(Modifier.padding(6.dp))
                }
            }
        }
    } else {
        MyListEmptyScreen()
    }
}