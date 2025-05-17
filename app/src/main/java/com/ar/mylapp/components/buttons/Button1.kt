package com.ar.mylapp.components.buttons
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun Button1Preview() {
    Button1(
        onClick = {},
        text = "Botón #1"
    )
}

@Composable
fun Button1(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(width = 270.dp, height = 85.dp)
            .border(
                width = 1.dp,
                color = GoldLight,
                shape = RoundedCornerShape(size = 20.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = TransparentGreen,
            contentColor = GoldDark),
        shape = RoundedCornerShape(20.dp)
    ) {
        /*Tittle1(
            tittle = text,
            modifier = Modifier
        )*/

        //Modificación de tamaño de Tittle1 a 30.sp y padding a 2.p (en vez de 32.dp y 16.dp)
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                fontWeight = FontWeight(400),
                color = GoldDark,
                textAlign = TextAlign.Center,
            ),
            maxLines = 2, // Mostrará hasta 2 líneas
        )
    }
}