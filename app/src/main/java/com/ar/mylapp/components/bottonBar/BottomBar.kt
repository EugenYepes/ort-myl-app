package com.ar.mylapp.components.bottonBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ar.mylapp.R
import com.ar.mylapp.Screens
import com.ar.mylapp.screens.AccountScreen
import com.ar.mylapp.screens.CardsScreen
import com.ar.mylapp.screens.DecksScreen
import com.ar.mylapp.screens.GuidebookScreen
import com.ar.mylapp.screens.HandScreen
import com.ar.mylapp.screens.HomeScreen
import com.ar.mylapp.screens.StoresScreen
import com.ar.mylapp.ui.theme.GoldLight
import com.ar.mylapp.ui.theme.TransparentGray

@Preview
@Composable
fun MyBottomAppBarPreview(){
    MyBottomAppBar()
}

@Composable
fun MyBottomAppBar(){
    /*
        rememberNavController() crea y recuerda el controlador de navegación (NavController), que se utiliza para gestionar la navegación entre pantallas.
        Usando remember se asegura que el estado de navegación se conserve a lo largo de las recomposiciones
        Se evita recrear el NavController innecesariamente.
    */
    val navigationController = rememberNavController()
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }
    val navItems = getBottomMenuContent()


    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Transparent,
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    navItems.forEach { item ->
                        IconButton(
                            onClick = {
                                selected.value = item.icon
                                navigationController.navigate(item.route){
                                    popUpTo(0)
                                }
                            },
                            modifier = Modifier.weight(1f),

                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = if (selected.value == item.icon) GoldLight else TransparentGray
                                )
                                Text(
                                    text = item.label,
                                    fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                                    color = if (selected.value == item.icon) GoldLight else TransparentGray,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        },
    ) {
            paddingValues -> // Valor de relleno (espaciado) generado automáticamente por el Scaffold.
                            // Se pasa al contenido para asegurarse de que no se solape con la barra inferior u otros elementos de la interfaz.

        /*
            Usamos un NavHost para gestionar la navegación entre las pantallas de la app.
            Aquí definimos las rutas (URLs) de las pantallas y asociamos cada una con un Composable específico.
            El NavHost escucha las rutas y, cuando se navega a una ruta, carga la pantalla correspondiente.
        */
        NavHost(
            navController = navigationController, // El controlador de navegación
            startDestination = Screens.Home.screen,  // Ruta inicial al abrir la app (pantalla Home)
            modifier = Modifier.padding(paddingValues) // Para ajustar los márgenes del contenido
        ) {
            composable(Screens.Home.screen) { HomeScreen() }
            composable(Screens.Cards.screen) { CardsScreen() }
            composable(Screens.Decks.screen) { DecksScreen() }
            composable(Screens.Stores.screen) { StoresScreen() }
            composable(Screens.Hand.screen) { HandScreen() }
            composable(Screens.Guidebook.screen) { GuidebookScreen() }
            composable(Screens.Account.screen) { AccountScreen() }
        }
    }
}
