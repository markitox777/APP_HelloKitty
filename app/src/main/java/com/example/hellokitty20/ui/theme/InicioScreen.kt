package com.example.hellokitty20.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InicioScreen(){


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement= Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        Text(text = "Es viernes !!!! waaaaa")
        Text(text = "y mi cuerpo lo sabe!!!")
        Text(text = "Saquense altoke el pisko")

        Spacer(modifier = Modifier.heightIn(48.dp))
        Row {

            Button(onClick = { }) { Text("Jugar Roblox") }
            Spacer(modifier = Modifier.heightIn(100.dp))
            Button(onClick = { }) { Text("ser un pete") }
        }
    }

}