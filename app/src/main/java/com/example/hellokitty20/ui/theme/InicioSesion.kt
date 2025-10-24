package com.example.hellokitty20.ui.theme

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults // <-- Importante para el color
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController // <-- ¡IMPORT NUEVO!

@Composable
fun InicioSesion (navController: NavController){ // <-- ¡CAMBIO 1: Acepta el NavController!

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var CorreoElectronico by remember {mutableStateOf("")}
    var numero by remember {mutableStateOf("")}
    var showDialog by remember { mutableStateOf(false) }

    //validacion correo
    var intentado by remember { mutableStateOf(false) }
    val correoValid = Patterns.EMAIL_ADDRESS.matcher(CorreoElectronico).matches()
    val errorCorreo = (!correoValid || CorreoElectronico.isBlank()) && intentado
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text="Nombre")
        //input 1
        OutlinedTextField(
            value = username,
            onValueChange = { nuevoTexto ->
                username = nuevoTexto
            },
            label = { Text("Introduzca su nombre") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.Red,
                disabledContainerColor = Color.Green
            )
        )
        Text(text = "Correo")
        OutlinedTextField(
            value = CorreoElectronico,
            onValueChange = { CorreoElectronico = it },
            label = { Text("Introduzca su Correo electronico") },
            // --- ¡NUEVO 3: Mostrar error si existe! ---
            isError = errorCorreo,
            supportingText = {
                if (errorCorreo) Text("Ingrese un correo válido")
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.Red,
                disabledContainerColor = Color.Green
            )
        )


        Text(text="Numero")
        //input 1
        OutlinedTextField(
            value = numero,
            onValueChange = { nuevoTexto ->
                numero = nuevoTexto
            },
            label = { Text("Introduzca su numero telefonico") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.Red,
                disabledContainerColor = Color.Green
            )
        )

        Text(text="Contraseña")
        //input 2
        OutlinedTextField(
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { nuevoTexto ->
                password = nuevoTexto
            },
            label = { Text("Introduzca una contraseña") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.Red,
                disabledContainerColor = Color.Green
            )
        )

        Button(
            onClick = {
                showDialog = true
            },

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Text(text = " Registrarse ")
        }
    }

    if (showDialog){
        AlertDialog(
            onDismissRequest = { /* No hacer nada */ },
            title = { Text("Se agregó Correctamente") },
            text = { Text("Muy Bien!! $username, correo: $CorreoElectronico, su numero: $numero, su contraseña es: $password") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    // --- ¡CAMBIO 3: Navega a la selección de personaje! ---
                    navController.navigate("seleccion")
                }) {
                    Text("Siguiente")
                }
            }
        )
    }
}