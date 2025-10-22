package com.example.hellokitty20 // Asegúrate que sea tu paquete

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

@Composable
fun MainGameScreen(navController: NavController, personaje: String?) {

    var accion by remember { mutableStateOf("saludando") }
    val nombreBasePersonaje = personaje ?: "hello_kitty"

    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        // ... (código del imageLoader sin cambios) ...
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo_hello_kitty),
            contentDescription = "Imagen de un gato",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            // --- ESTA PARTE MUESTRA EL GIF (funciona para los dos) ---
            val nombreDelGif = "${nombreBasePersonaje}_${accion}"
            val resourceId =
                context.resources.getIdentifier(nombreDelGif, "drawable", context.packageName)

            if (resourceId != 0) {
                Image(
                    painter = rememberAsyncImagePainter(resourceId, imageLoader),
                    contentDescription = "Personaje",
                    modifier = Modifier
                        .size(300.dp)
                        .padding(top = 40.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center


                )
            } else {
                Spacer(modifier = Modifier.size(300.dp).padding(top = 40.dp))
                Text(text = "GIF no encontrado para $nombreDelGif", color = Color.Red)
            }


            Spacer(modifier = Modifier.height(30.dp))

            // --- Hello Kitty juego---
            if (nombreBasePersonaje == "hello_kitty") {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Button(onClick = { accion = "saludando" }) { Text(text = "Saludar") }
                    Button(onClick = { accion = "jugando" }) { Text(text = "Jugar") }
                    Button(onClick = { accion = "despidiendose" }) { Text(text = "Despedirse") }
                }
            }
            // --- Keroppy Juego ---
            else if (nombreBasePersonaje == "keroppi") {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Button(onClick = { accion = "saludando" }) { Text(text = "Saludar") }
                    Button(onClick = { accion = "comiendo" }) { Text(text = "Comer") }
                    Button(onClick = { accion = "despidiendose" }) { Text(text = "Despedirse") }
                }
            }



            // Volver y elegir otro personaje
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Magenta
                )
            ) {
                Text(text = "Elegir otro personaje")
            }
        }
    }
}