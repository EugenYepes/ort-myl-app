package com.ar.mylapp.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.GoldLight
import com.ar.mylapp.ui.theme.TransparentGreen
import com.ar.mylapp.ui.theme.White

@Preview
@Composable
fun Button6Preview(){
    Button6(
        onClick = { },
        text = "CUENTA",
        icon =  painterResource(id = R.drawable.store_icon),
    )
}

@Composable
fun Button6(
    onClick: () -> Unit,
    text: String,
    icon: Painter
){
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(
                width = 152.dp,
                height = 56.dp
            )
            .border(
                width = 1.dp,
                color = GoldLight,
                shape = RoundedCornerShape(size = 20.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = TransparentGreen,
            contentColor = White
        ),
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                contentScale = ContentScale.None,
            )
            Text(
                text = text,
                fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                fontSize = 16.sp,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
        }
    }
}