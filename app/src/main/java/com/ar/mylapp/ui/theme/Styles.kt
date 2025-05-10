package com.ar.mylapp.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R

//TextStyle
val searchBarStyle = TextStyle(
    fontSize = 20.sp,
    lineHeight = 18.sp,
    fontFamily = FontFamily(Font(R.font.patua_one_regular)),
    fontWeight = FontWeight(400),
    color = Gray,
    background = Color.Transparent
)

val labelStyle = TextStyle(
    fontSize = 15.sp,
    lineHeight = 18.sp,
    fontFamily = FontFamily(Font(R.font.patua_one_regular)),
    fontWeight = FontWeight(400),
    color = GoldDark,
    background = Color.Transparent
)

val inputOneStyle = TextStyle(
    fontSize = 15.sp,
    lineHeight = 18.sp,
    fontFamily = FontFamily(Font(R.font.patua_one_regular)),
    fontWeight = FontWeight(400),
    color = White
)

val inputTwoThreeStyle = TextStyle(
    fontSize = 15.sp,
    lineHeight = 18.sp,
    fontFamily = FontFamily(Font(R.font.patua_one_regular)),
    fontWeight = FontWeight(400),
    background = Color.Transparent
)

val textDropdownMenuStyle = TextStyle(
    fontSize = 18.sp,
    lineHeight = 16.sp,
    fontFamily = FontFamily(Font(R.font.patua_one_regular)),
    fontWeight = FontWeight(400),
    color = White
)

val labelDropdownMenuStyle = TextStyle(
    fontSize = 12.sp,
    lineHeight = 10.sp,
    fontFamily = FontFamily(Font(R.font.patua_one_regular)),
    fontWeight = FontWeight(400),
    color = GoldDark
)


//OutlinedTexStyle

@Composable
fun outlinedTextFieldOneStyle() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = GoldDark,
    unfocusedBorderColor = GoldDark,
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    disabledContainerColor = Black,
    focusedLabelColor = GoldDark,
    unfocusedLabelColor = Black,
    cursorColor = GoldDark
)

@Composable
fun outlinedTextFieldTwoThreeStyle() = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = Gray,
    unfocusedContainerColor = DarkGray,
    disabledContainerColor = DarkGray,
    focusedBorderColor = GoldDark,
    unfocusedBorderColor = Black,
    focusedLabelColor = GoldDark,
    unfocusedLabelColor = Gray,
    cursorColor = GoldDark
)

//ExposedDropdownMenu
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun exposedDropdownStyle() = ExposedDropdownMenuDefaults.textFieldColors(
    focusedTextColor = White,
    unfocusedTextColor = White,
    focusedContainerColor = Black,
    unfocusedContainerColor = Black,
    focusedLabelColor = GoldDark,
    unfocusedLabelColor = GoldDark,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent
)


