package com.nugu.search.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.nugu.nuguollim.common.data.model.template.Template
import com.nugu.nuguollim.common.data.model.template.TemplateSort
import com.nugu.nuguollim.design_system.component.NuguDropDownMenu
import com.nugu.nuguollim.design_system.component.NuguFillButton
import com.nugu.nuguollim.design_system.component.NuguTemplateItem
import com.nugu.nuguollim.design_system.component.PretendardText
import com.nugu.nuguollim.design_system.theme.Gray100
import com.nugu.nuguollim.design_system.theme.Gray700
import com.nugu.nuguollim.design_system.theme.Primary500
import com.nugu.search.R
import com.nugu.ui_core.StringUtil
import kotlinx.coroutines.flow.Flow

@Composable
fun TemplateSearchScreen(
    keyword: String,
    templatesData: Flow<PagingData<Template>>,
    onSortChanged: (TemplateSort) -> Unit,
    onFavorite: (Long, Boolean) -> Unit,
    onNavigateToSearchHome: () -> Unit,
    onClickTemplate: (Template) -> Unit = {}
) {
    val templates = templatesData.collectAsLazyPagingItems()

    if (templates.itemCount > 0) {
        Column {
            Spacer(modifier = Modifier.padding(top = 20.dp))
            PretendardText(
                text = String.format(
                    stringResource(R.string.description_search_template_title),
                    keyword
                ),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                style = MaterialTheme.typography.h2
            )
            PretendardText(
                text = stringResource(R.string.description_search_template_tip),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Gray700,
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
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
                        val target = StringUtil.spannableAll(
                            text = contentData.target,
                            keyword = keyword,
                            style = SpanStyle(color = Primary500)
                        )
                        val label = StringUtil.spannableAll(
                            text = contentData.theme,
                            keyword = keyword,
                            style = SpanStyle(color = Primary500)
                        )
                        val content = StringUtil.spannableAll(
                            text = contentData.content,
                            keyword = keyword,
                            style = SpanStyle(color = Primary500)
                        )
                        NuguTemplateItem(
                            target = target,
                            label = label,
                            content = content,
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
    } else {
        TemplateSearchEmptyScreen(
            modifier = Modifier.padding(bottom = 2.dp),
            keyword = keyword,
            onNavigateToSearchHome = onNavigateToSearchHome
        )
    }
}

@Composable
fun TemplateSearchEmptyScreen(
    modifier: Modifier = Modifier,
    keyword: String,
    onNavigateToSearchHome: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.padding(top = 56.dp))
        Image(
            painter = painterResource(R.drawable.image_search_empty),
            contentDescription = "Search Empty"
        )
        PretendardText(
            text = String.format(
                stringResource(R.string.description_search_template_empty_title),
                keyword
            ),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(top = 20.dp),
            textAlign = TextAlign.Center
        )
        PretendardText(
            text = stringResource(R.string.description_search_template_empty_tip),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Gray700,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(top = 10.dp)
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))
        NuguFillButton(
            text = stringResource(R.string.description_search_template_empty_button_text),
            onClick = onNavigateToSearchHome
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray100, RoundedCornerShape(5.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            PretendardText(
                text = stringResource(R.string.description_search_template_empty_tip_title),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 12.dp)
            )
            PretendardText(
                text = stringResource(R.string.description_search_template_empty_tip_sub1),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Gray700,
                modifier = Modifier.padding(top = 4.dp)
            )
            PretendardText(
                text = stringResource(R.string.description_search_template_empty_tip_sub2),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Gray700,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}