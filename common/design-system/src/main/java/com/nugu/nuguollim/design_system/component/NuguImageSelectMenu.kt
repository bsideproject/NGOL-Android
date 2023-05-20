package com.nugu.nuguollim.design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.snapshots.SnapshotStateObserver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nugu.nuguollim.common.data.model.paper.Paper
import com.nugu.nuguollim.common.data.model.paper.PaperTheme
import com.nugu.nuguollim.design_system.R
import com.nugu.nuguollim.design_system.theme.Gray500

@Composable
fun NuguImageSelectMenu(
    modifier: Modifier = Modifier,
    papers: List<Paper> = listOf(),
    onSelectColor: (Color) -> Unit = {},
    onSelectBackground: (String) -> Unit = {}
) {
    var openColorDialog by remember { mutableStateOf(false) }
    var selectType by remember { mutableStateOf(PaperTheme.CRAFT) }
    var currentPaperList by remember { mutableStateOf(papers.filter { it.theme == selectType }) }
    val snapshotStateObserver =
        remember { SnapshotStateObserver { Snapshot.sendApplyNotifications() } }
    val colorPainter = painterResource(id = R.drawable.ic_color_picker)

    LaunchedEffect(selectType) {
        snapshotStateObserver.observeReads(
            scope = selectType,
            onValueChangedForScope = {}
        ) {
            val filteredList = papers.filter { it.theme == selectType }
            currentPaperList = filteredList
        }
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        openColorDialog = true
                    },
                painter = colorPainter,
                contentDescription = "color picker"
            )
            NuguChip(
                text = PaperTheme.CRAFT.title,
                isChecked = selectType == PaperTheme.CRAFT,
                onClick = { selectType = PaperTheme.CRAFT }
            )
            NuguChip(
                text = PaperTheme.TIDY.title,
                isChecked = selectType == PaperTheme.TIDY,
                onClick = { selectType = PaperTheme.TIDY }
            )
            NuguChip(
                text = PaperTheme.FUNNY.title,
                isChecked = selectType == PaperTheme.FUNNY,
                onClick = { selectType = PaperTheme.FUNNY }
            )
        }
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            items(currentPaperList) { paper ->
                AsyncImage(
                    modifier = Modifier
                        .size(62.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .clickable { onSelectBackground(paper.img) },
                    model = paper.thumbImg,
                    contentDescription = null
                )
            }
        }
    }

    if (openColorDialog) {
        HsvColorPickerDialog {
            onSelectColor(it)
            openColorDialog = false
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 480)
@Composable
private fun NuguImageSelectMenuPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray500),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NuguImageSelectMenu()
    }
}