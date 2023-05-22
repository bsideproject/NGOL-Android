package com.nugu.search.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.nugu.nuguollim.common.data.model.search.target.TemplateTargetData
import com.nugu.nuguollim.common.data.model.search.target.ThemeData
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.common.data.model.template.TemplateSort
import com.nugu.nuguollim.design_system.component.NuguChip
import com.nugu.nuguollim.design_system.component.NuguDropDownMenu
import com.nugu.nuguollim.design_system.component.NuguTemplateItem
import com.nugu.nuguollim.design_system.component.PretendardText
import com.nugu.nuguollim.design_system.theme.Gray700
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.search.R
import com.nugu.ui_core.singleClick
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun ThemeSearchScreen(
    targetData: TemplateTargetData,
    templatesData: Flow<PagingData<Template>>,
    onSortChanged: (TemplateSort) -> Unit,
    onThemeSelectChanged: (Int?) -> Unit,
    onFavorite: (Long, Boolean) -> Unit,
    onClickTemplate: (Template) -> Unit = {}
) {
    val templates = templatesData.collectAsLazyPagingItems()
    var isCheckThemeId by remember { mutableStateOf<Int?>(null) }

    Column {
        PretendardText(
            text = targetData.themeTitle,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        PretendardText(
            text = stringResource(R.string.description_search_theme_tip),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp),
            color = Gray700
        )
        Spacer(modifier = Modifier.padding(top = 12.dp))
        singleClick { singleClickListener ->
            FlowRow {
                targetData.themes.forEach { themeData ->
                    NuguChip(
                        modifier = Modifier.padding(top = 8.dp).height(33.dp),
                        text = themeData.name,
                        isChecked = isCheckThemeId == themeData.id,
                        onClick = {
                            singleClickListener.onClick {
                                isCheckThemeId =
                                    if (isCheckThemeId != themeData.id) themeData.id else null
                                onThemeSelectChanged.invoke(isCheckThemeId)
                            }
                        }
                    )
                    Spacer(Modifier.padding(horizontal = 4.dp))
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            NuguDropDownMenu(
                items = TemplateSort.values().toList(),
                onSelectItem = { onSortChanged(it) }
            )
        }
        LazyColumn {
            items(templates) { contentData ->
                if (contentData != null) {
                    var isFavorite by remember { mutableStateOf(contentData.favorite) }

                    NuguTemplateItem(
                        label = contentData.theme,
                        content = contentData.content,
                        isFavorite = isFavorite,
                        onClickFavorite = {
                            isFavorite = !isFavorite
                            onFavorite.invoke(contentData.id, isFavorite)
                        },
                        onClick = { onClickTemplate.invoke(contentData) }
                    )
                    Spacer(Modifier.padding(6.dp))
                }
            }
        }
    }
}

private val TemplateTargetData.themeTitle: String
    @Composable get() = String.format(stringResource(R.string.description_search_theme_title), name)


@Preview
@Composable
private fun ThemeSearchScreenPreview() {
    NuguollimTheme {
        ThemeSearchScreen(
            targetData = TemplateTargetData(
                id = 4,
                name = "교수님",
                children = listOf(),
                depth = 0,
                themes = listOf(
                    ThemeData(0, "성적 문의"),
                    ThemeData(1, "출결 문의"),
                    ThemeData(2, "감사 인사"),
                    ThemeData(3, "적성 문의"),
                    ThemeData(4, "경출 문의"),
                    ThemeData(5, "사감 인사"),
                    ThemeData(6, "문의 성적"),
                    ThemeData(7, "문의 출결"),
                    ThemeData(8, "인사 감사"),
                    ThemeData(9, "사인 감사"),
                    ThemeData(10, "의문 정석"),
                )
            ),
            templatesData = flowOf(PagingData.from(listOf())),
            onSortChanged = {},
            onThemeSelectChanged = {},
            onFavorite = { _, _ -> }
        )
    }
}