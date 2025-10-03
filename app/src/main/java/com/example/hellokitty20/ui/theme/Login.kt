package com.example.hellokitty20.ui.theme

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hellokitty20.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

/**
 * Composable que gestiona la pantalla de login.
 * Presenta campos para nombre, correo y contraseña,
 * y permite autenticar con Firebase Auth usando email y password.
 * Muestra feedback usando Snackbar para errores y AlertDialog en login exitoso.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    val auth = Firebase.auth
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Scaffold que contiene SnackbarHost para mostrar mensajes de error o aviso
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            Image(
                painter = painterResource(id = R.drawable.inicio),
                contentDescription = "Imagen de un gato",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = "Registremonos para más diversión",
                color = Color.White,
                modifier = Modifier.align(Alignment.TopCenter).padding(100.dp)
            )

            // Columna principal con los inputs de usuario y botón
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Campo para nombre de usuario
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Introduzca su nombre") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.Red,
                        disabledContainerColor = Color.Green
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Campo para contraseña con máscara
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Introduzca una contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.Red,
                        disabledContainerColor = Color.Green
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Campo para correo electrónico
                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Introduzca su correo") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.Red,
                        disabledContainerColor = Color.Green
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón para iniciar sesión
                Button(onClick = {
                    // Validación básica de campos vacíos
                    if (correo.isBlank() || password.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Correo y contraseña no pueden estar vacíos")
                        }
                        return@Button
                    }

                    // Intentar login con Firebase Authentication
                    auth.signInWithEmailAndPassword(correo, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Login exitoso
                                dialogMessage = "Bienvenido, $username"
                                showDialog = true
                            } else {
                                // En caso de error, mostrar mensaje snackbar con el error
                                val error = task.exception?.message ?: "Error desconocido"
                                scope.launch {
                                    snackbarHostState.showSnackbar(error)
                                }
                            }
                        }
                }) {
                    Text(text = " Enviar ")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Botón para volver a la página1 (ajusta la ruta según tu navegación)
            Button(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                onClick = { navController.navigate("pagina1") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Volver a la página 1")
            }
        }
    }
        // Diálogo de bienvenida tras login exitoso
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { /* No cerrar al hacer clic fuera */ },
                title = { Text("Login") },
                text = { Text(dialogMessage) },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }



