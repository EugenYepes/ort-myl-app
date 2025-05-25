package com.ar.mylapp.components.number_picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ar.mylapp.R
import com.ar.mylapp.components.buttons.PickerButton
import com.ar.mylapp.viewmodel.NumberPickerViewModel

@Preview(showBackground = true, backgroundColor = 0xFF00FF00)
@Composable
fun NumberPickerPreview(){
    NumberPicker(
        modifier = Modifier.width(150.dp),
        min = 0,
        max = 5,
    )
}

@Composable
fun NumberPicker(
    modifier: Modifier = Modifier,
    min: Int = 0,
    max: Int = 10,
    viewModel: NumberPickerViewModel = viewModel()
) {
    val number = viewModel.number

    Row(
        modifier = modifier
            .width(200.dp)
            .background(
                color = Color(0xFF22211F),
                shape = RoundedCornerShape(100.dp)
            )
            .padding(
                horizontal = 20.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        PickerButton(
            icon = stringResource(R.string.remove),
            modifier = Modifier.weight(1f),
            onClick = { viewModel.decrement(min) }
        )

        Text(
            text = number.toString(),
            color = Color(0xFFF5EEC1),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f),
        )

        PickerButton(
            icon = stringResource(R.string.add),
            modifier = Modifier.weight(1f),
            onClick = { viewModel.increment(max) }
        )
    }
}


