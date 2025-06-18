package com.ar.mylapp.components.accordion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.GoldLight

@Preview
@Composable
fun GuidebookAccordion() {

    val guidebookListTitles = listOf(
        stringResource(id = R.string.guidebook_one_objective_title),
        stringResource(id = R.string.guidebook_two_objective_title),
        stringResource(id = R.string.guidebook_three_objective_title),
        stringResource(id = R.string.guidebook_four_objective_title),
        stringResource(id = R.string.guidebook_five_objective_title),
        stringResource(id = R.string.guidebook_six_objective_title)
    )

    val guidebookListTexts = listOf(
        stringResource(id = R.string.guidebook_one_objective_text),
        stringResource(id = R.string.guidebook_two_objective_text),
        stringResource(id = R.string.guidebook_three_objective_text),
        stringResource(id = R.string.guidebook_four_objective_text),
        stringResource(id = R.string.guidebook_five_objective_text),
        stringResource(id = R.string.guidebook_six_objective_text)
    )

    // Solo guarda el índice actualmente expandido, para que los demás items expandidos se cierren
    var expandedIndex by remember { mutableStateOf<Int?>(null) }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .fillMaxSize()
    ) {
        items(guidebookListTitles.size) { index ->
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clickable {
                        expandedIndex = if (expandedIndex == index) null else index
                    }
            ) {
                GuidebookTitleBox(title = guidebookListTitles[index])

                // Animación al expandir o colapsar el contenido
                AnimatedVisibility(visible = expandedIndex == index) {
                    GuidebookTextBox(text = guidebookListTexts[index])
                }
                HorizontalDivider(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth(),
                    color = GoldLight
                )
            }
        }
    }
}