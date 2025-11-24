package com.example.hellokitty20.ui.theme

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hellokitty20.ViewModel.TuViewModel
import kotlinx.coroutines.launch

@Composable
fun InicioSesion(navController: NavController) {
    // ViewModel
    val viewModel: TuViewModel = viewModel()
    val scope = rememberCoroutineScope()

    // Estados de los campos
    var username by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Mensajes de error (null = sin error)
    var usernameError by remember { mutableStateOf<String?>(null) }
    var correoError by remember { mutableStateOf<String?>(null) }
    var numeroError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    // Diálogo de confirmación
    var showDialog by remember { mutableStateOf(false) }

    // Validaciones simples (se recalculan conforme el usuario escribe)
    fun validateUsername(value: String) {
        usernameError = if (value.isBlank()) "El nombre no puede estar vacío" else null
    }

    fun validateCorreo(value: String) {
        correoError = when {
            value.isBlank() -> "El correo no puede estar vacío"
            !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> "Correo no válido"
            else -> null
        }
    }

    fun validateNumero(value: String) {
        numeroError = when {
            value.isBlank() -> "El número no puede estar vacío"
            !value.all { it.isDigit() } -> "Solo se permiten números"
            else -> null
        }
    }

    fun validatePassword(value: String) {
        passwordError = when {
            value.isBlank() -> "La contraseña no puede estar vacía"
            value.length < 4 -> "La contraseña debe tener al menos 4 caracteres"
            else -> null
        }
    }

    // Estado del formulario válido: todos los errores son null y campos no vacíos
    val isFormValid = listOf(usernameError, correoError, numeroError, passwordError).all { it == null }
            && username.isNotBlank()
            && correo.isNotBlank()
            && telefono.isNotBlank()
            && password.isNotBlank()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Nombre
        Text("Nombre")
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                validateUsername(it)
            },
            label = { Text("Nombre") },
            isError = usernameError != null,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.Red,
                disabledContainerColor = Color.Green
            )
        )
        if (usernameError != null) {
            Text(text = usernameError!!, color = Color.Red, modifier = Modifier.height(20.dp))
        } else {
            Spacer(modifier = Modifier.height(20.dp))
        }

        // Correo
        Text("Correo electronico")
        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
                validateCorreo(it)
            },
            label = { Text("Correo electrónico") },
            isError = correoError != null,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.Red,
                disabledContainerColor = Color.Green
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        if (correoError != null) {
            Text(text = correoError!!, color = Color.Red, modifier = Modifier.height(20.dp))
        } else {
            Spacer(modifier = Modifier.height(20.dp))
        }

        // Número - acepta solo dígitos y muestra teclado numérico
        Text("Numero Telefonico")
        OutlinedTextField(
            value = telefono,
            onValueChange = { nuevo ->
                // Opción: permitir solo dígitos mientras escribe
                if (nuevo.all { it.isDigit() } || nuevo.isBlank()) {
                    telefono = nuevo
                    validateNumero(nuevo)
                } else {
                    // Si quieres permitir que el usuario escriba y luego mostrar error,
                    // comenta el if/else y usa: numero = nuevo; validateNumero(nuevo)
                }
            },
            label = { Text("Número telefónico") },
            isError = numeroError != null,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.Red,
                disabledContainerColor = Color.Green
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (numeroError != null) {
            Text(text = numeroError!!, color = Color.Red, modifier = Modifier.height(20.dp))
        } else {
            Spacer(modifier = Modifier.height(20.dp))
        }

        // Contraseña
        Text("Contraseña")
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                validatePassword(it)
            },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError != null,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.Red,
                disabledContainerColor = Color.Green
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        if (passwordError != null) {
            Text(text = passwordError!!, color = Color.Red, modifier = Modifier.height(20.dp))
        } else {
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Botón: solo habilitado si isFormValid == true
        Button(
            onClick = {
                scope.launch {
                    val ok = viewModel.registerUser(username, correo, password, telefono)

                    if (ok) {
                        showDialog = true
                    } else {
                        usernameError = "No se pudo registrar en Xano"
                    }
                }
            },
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Registrarse")
        }
    }

    // Dialogo de confirmación
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Se agregó Correctamente") },
            text = {
                Text("Muy Bien!! $username\nCorreo: $correo\nNúmero: $telefono\nContraseña: $password")
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    navController.navigate("seleccion")
                }) {
                    Text("Siguiente")
                }
            }
        )
    }
}

