package com.ar.mylapp.components.number_picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ar.mylapp.R
import com.ar.mylapp.components.buttons.PickerButton
import com.ar.mylapp.ui.theme.BlackLight
import com.ar.mylapp.ui.theme.GoldBeige
import com.ar.mylapp.viewmodel.NumberPickerViewModel

@Composable
fun NumberPicker(
    modifier: Modifier = Modifier,
    min: Int = 0,
    max: Int = 3,
    number: Int,
    onValueChange: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = BlackLight,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        PickerButton(
            icon = stringResource(R.string.remove),
            modifier = Modifier.size(28.dp),
            onClick = {
                val newValue = (number - 1).coerceAtLeast(min)
                onValueChange(newValue)
            }
        )

        Text(
            text = number.toString(),
            color = GoldBeige,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        PickerButton(
            icon = stringResource(R.string.add),
            modifier = Modifier.size(28.dp),
            onClick = {
                val newValue = (number + 1).coerceAtMost(max)
                onValueChange(newValue)
            }
        )
    }
}