package com.example.hellokitty20.ui.theme

// (Todos tus imports de antes...)
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hellokitty20.MainGameScreen
import com.example.hellokitty20.R
import com.example.hellokitty20.SelectCharacterScreen


@Composable
fun Navegacion() {
    val navController = rememberNavController()

    // Tu app sigue empezando en "pagina1"
    NavHost(navController, startDestination = "pagina1") {

        // Tus rutas originales
        composable("Pagina1") { Pagina1(navController) }
        composable("Pagina2") { Pagina2(navController) }


        // --- Rutas nuevas para el juego ---
        composable("seleccion") {
            SelectCharacterScreen(navController = navController)
        }
        composable(
            route = "game/{personaje}",
            arguments = listOf(navArgument("personaje") { type = NavType.StringType })
        ) { backStackEntry ->
            val personaje = backStackEntry.arguments?.getString("personaje")
            MainGameScreen(navController = navController, personaje = personaje)
        }
    }
}


// TU Pagina1 (SIN CAMBIOS)
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


// TU Pagina2 (CON LA CONEXIÓN)
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

                // --- ¡CONEXIÓN CLAVE! ---
                // Aquí le pasa el navController a tu InicioSesion
                InicioSesion(navController = navController)
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