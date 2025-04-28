package com.ar.mylapp.components.buttons
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.GoldLight
import com.ar.mylapp.ui.theme.TransparentGreen

@Preview
@Composable
fun ButtonPrimaryPreview() {
    ButtonPrimary(
        onClick = {},
        text = "Botón #1"
    )
}

@Composable
fun ButtonPrimary(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = onClick,
        //contentPadding = PaddingValues(horizontal = 50.dp, vertical = 40.dp),
        modifier = Modifier
            .size(width = 270.dp, height = 85.dp)
            .border(
                width = 4.dp, // Originalmente en 1.dp
                color = GoldLight,
                shape = RoundedCornerShape(size = 20.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = TransparentGreen,
            contentColor = GoldDark),
        shape = RoundedCornerShape(20.dp))
    {
        Text(
            //modifier = Modifier.size(width = 268.dp, height = 18.dp),
            //modifier = Modifier.fillMaxSize(),
            text = text,
            style = TextStyle(
                fontSize = 32.sp,
                lineHeight = 36.sp,
                fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                fontWeight = FontWeight(400),
                color = GoldDark,
                textAlign = TextAlign.Center,
            )
        )
    }
}