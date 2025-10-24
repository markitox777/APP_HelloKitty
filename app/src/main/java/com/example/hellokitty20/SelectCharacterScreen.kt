package com.example.hellokitty20 // Asegúrate que sea tu paquete

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hellokitty20.R


@Composable
fun SelectCharacterScreen(navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.fondo_hello_kitty),
            contentDescription = "Imagen de fondo para seleccionar personaje",
            contentScale= ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Elige tu personaje",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 30.dp)
            )

            // Botón para Kitty
            Image(
                painter = painterResource(id = R.drawable.kitty_select2),
                contentDescription = "Elegir Kitty",
                modifier = Modifier
                    .size(150.dp)
                    .clickable {
                        // Llamamos al juego de hello kitty
                        navController.navigate("game/hello_kitty")
                    }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Botón para Keroppi
            Image(
                painter = painterResource(id = R.drawable.keroppi_select),
                contentDescription = "Elegir Keroppi",
                modifier = Modifier
                    .size(150.dp)
                    .clickable {
                        //llamamos al juego de keropy
                        navController.navigate("game/keroppi")
                    }
            )
        }
        Button(
            onClick = { navController.navigate("pagina1")},
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(text = "Volver al inicio!!", color = Color.Red)
        }
    }
}