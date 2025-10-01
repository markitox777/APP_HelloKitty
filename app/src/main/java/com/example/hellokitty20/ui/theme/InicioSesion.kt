package com.example.hellokitty20.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.w3c.dom.Text

@Composable
fun InicioSesion (){


    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement= Arrangement.Center,
        horizontalAlignment= Alignment.CenterHorizontally

    )   {

        Text(text="Nombre")
        //input 1
        OutlinedTextField(
            value = username,
            onValueChange = { nuevoTexto ->
                username = nuevoTexto
            }, label = { Text("Introduzca su nombre")} //Aqui hacemos un texto dentro del input para que describir lo que debe poner
        )
        Text(text="Contraseña")
        //input 2
        OutlinedTextField(
            value = password,
            onValueChange = { nuevoTexto ->
                password = nuevoTexto
            }, label = {Text("Introduzca una contraseña")}
        )

        Button(onClick = {
            showDialog = true
        }) {
            Text(text = " Enviar ")
        }

    }


    if (showDialog){
        AlertDialog(
            onDismissRequest = { },
            title = {Text("Se agrego Correctamente")},
            text= { Text("Gracias $username, " +
                    "su contraseña es: $password")},
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