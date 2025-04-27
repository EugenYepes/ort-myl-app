package com.ar.mylapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ar.mylapp.components.bottonBar.MyBottomAppBar
import com.ar.mylapp.ui.theme.MYLAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MYLAPPTheme {
                MyBottomAppBar()
            }
        }
    }
}