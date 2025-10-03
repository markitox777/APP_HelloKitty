package com.example.hellokitty20.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun InicioSesion (){

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text="Nombre")
        //input 1 - CORREGIDO
        OutlinedTextField(
            value = username,
            onValueChange = { nuevoTexto ->
                username = nuevoTexto
            },
            label = { Text("Introduzca su nombre") },
            // ----- INICIO DE LA CORRECCIÓN -----
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,    // Color de fondo cuando está seleccionado
                unfocusedContainerColor = Color.Red,  // Color de fondo cuando no está seleccionado
                disabledContainerColor = Color.Green   // Color de fondo si estuviera deshabilitado
            )
            // ----- FIN DE LA CORRECCIÓN -----
        )

        Text(text="Contraseña")
        //input 2 - CORREGIDO
        OutlinedTextField(
            value = password,
            onValueChange = { nuevoTexto ->
                password = nuevoTexto
            },
            label = { Text("Introduzca una contraseña") },
            // ----- INICIO DE LA CORRECCIÓN -----
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.Red,
                disabledContainerColor = Color.Green
            )
            // ----- FIN DE LA CORRECCIÓN -----
        )

        Button(onClick = {
            showDialog = true
        }) {
            Text(text = " Enviar ")
        }
    }

    if (showDialog){
        AlertDialog(
            onDismissRequest = { /* No hacer nada para forzar el clic en el botón */ },
            title = { Text("Se agregó Correctamente") },
            text = { Text("Gracias $username, su contraseña es: $password") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                }) {
                    Text("Siguiente")
                }
            }
        )
    }
}
