package com.nugu.search.ui

import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nugu.nuguollim.design_system.component.NuguChip
import com.nugu.nuguollim.design_system.component.PretendardText
import com.nugu.nuguollim.design_system.theme.NuguollimTheme
import com.nugu.ui_core.singleClick
import com.nuguollim.data.model.search.target.TemplateTargetData
import com.nuguollim.data.model.search.target.ThemeData

@Composable
fun TargetSearchScreen(
    templateTargets: List<TemplateTargetData> = listOf(),
    modifier: Modifier = Modifier,
    onClick: (TemplateTargetData) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(templateTargets) { item ->
            Spacer(modifier = Modifier.padding(top = 20.dp))

            PretendardText(
                text = item.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            singleClick { singleClickListener ->
                FlowRow(modifier = Modifier.fillMaxSize()) {
                    item.children.forEach { templateTargetData ->
                        var isCheck by remember { mutableStateOf(false) }
                        NuguChip(
                            text = templateTargetData.name,
                            isChecked = isCheck,
                            onClick = {
                                singleClickListener.onClick {
                                    isCheck = !isCheck
                                    onClick.invoke(templateTargetData)
                                }
                            }
                        )
                        Spacer(Modifier.padding(4.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun TargetsPreview() {
    NuguollimTheme {
        TargetSearchScreen(templateTargets) {

        }
    }
}

private val childTemplateTargets = listOf(
    TemplateTargetData(
        id = 0,
        name = "아무말",
        children = listOf(),
        depth = 0,
        themes = listOf(ThemeData(0, "대잔치"))
    ),
    TemplateTargetData(
        id = 1,
        name = "아무말1",
        children = listOf(),
        depth = 0,
        themes = listOf(ThemeData(0, "대잔치"))
    ),
    TemplateTargetData(
        id = 2,
        name = "아무말2",
        children = listOf(),
        depth = 0,
        themes = listOf(ThemeData(0, "대잔치"))
    ),
    TemplateTargetData(
        id = 3,
        name = "아무말3",
        children = listOf(),
        depth = 0,
        themes = listOf(ThemeData(0, "대잔치"))
    ),
    TemplateTargetData(
        id = 4,
        name = "아무말4",
        children = listOf(),
        depth = 0,
        themes = listOf(ThemeData(0, "대잔치"))
    ),
    TemplateTargetData(
        id = 5,
        name = "아무말5",
        children = listOf(),
        depth = 0,
        themes = listOf(ThemeData(0, "대잔치"))
    ),
    TemplateTargetData(
        id = 6,
        name = "아무말6",
        children = listOf(),
        depth = 0,
        themes = listOf(ThemeData(0, "대잔치"))
    ),
)

private val templateTargets = listOf(
    TemplateTargetData(
        id = 0,
        name = "아무말",
        children = childTemplateTargets,
        depth = 0,
        themes = listOf(ThemeData(0, "대잔치"))
    ),
    TemplateTargetData(
        id = 0,
        name = "아무말",
        children = childTemplateTargets,
        depth = 0,
        themes = listOf(ThemeData(0, "대잔치"))
    ),
    TemplateTargetData(
        id = 0,
        name = "아무말",
        children = childTemplateTargets,
        depth = 0,
        themes = listOf(ThemeData(0, "대잔치"))
    ),
    TemplateTargetData(
        id = 0,
        name = "아무말",
        children = childTemplateTargets,
        depth = 0,
        themes = listOf(ThemeData(0, "대잔치"))
    ),
    TemplateTargetData(
        id = 0,
        name = "아무말",
        children = childTemplateTargets,
        depth = 0,
        themes = listOf(ThemeData(0, "대잔치"))
    ),
)