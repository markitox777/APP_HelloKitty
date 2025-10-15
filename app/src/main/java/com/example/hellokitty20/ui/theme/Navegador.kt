package com.example.hellokitty20.ui.theme

import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hellokitty20.R


@Composable
fun Navegacion() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "pagina1") {
        composable("Pagina1") { Pagina1(navController) }
        composable("Pagina2") { Pagina2(navController) }
        }
    }

@Composable
fun Pagina1(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.imagenlogin),
            contentDescription = "Imagen de un gato",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hola!!", color = Color.White)
            Text(text = "Bienvenido a Hello Kitty", color = Color.White)
        }
        Button(
            onClick = { navController.navigate("pagina2")},
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(text = "Sigueme!!", color = Color.Red)
        }
    }
}
@Composable
fun Pagina2(navController: NavController) {
    HelloKitty20Theme {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.inicio),
                contentDescription = "Imagen de fondo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )



            // Contenido encima del fondo
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Registremonos", color = Color.White)
                Text(text = "Para que podamos jugar juntos!!", color = Color.White)
                Spacer(modifier = Modifier.height(48.dp))
                InicioSesion()
            }
            Button(
                onClick = { navController.navigate("pagina1")},
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(text = "Volvamos atras!!", color = Color.Red)
            }
        }
    }
}