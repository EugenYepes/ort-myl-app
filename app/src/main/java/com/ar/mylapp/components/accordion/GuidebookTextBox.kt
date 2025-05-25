package com.ar.mylapp.components.accordion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.mylapp.R
import com.ar.mylapp.components.text.Text9
import com.ar.mylapp.ui.theme.BlackLight

@Preview
@Composable
fun GuidebookTextPreview() {
    GuidebookTextBox(
        text = stringResource(R.string.guidebook_one_objective_text)
    )
}

@Composable
fun GuidebookTextBox(
    text: String
) {
    Box(
        modifier = Modifier
            .width(365.dp)
            .background(color = BlackLight)
            .padding(horizontal = 16.dp, vertical = 5.dp),
    ) {
        Text9(
            text = text
        )
    }
}