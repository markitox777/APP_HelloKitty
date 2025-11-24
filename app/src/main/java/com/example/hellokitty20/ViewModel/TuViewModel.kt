package com.example.hellokitty20.ViewModel

import androidx.lifecycle.ViewModel
import com.example.hellokitty20.data.model.LoginRequest
import com.example.hellokitty20.data.model.RegisterRequest
import com.example.hellokitty20.data.remote.ApiClient
import com.example.hellokitty20.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TuViewModel : ViewModel() {

    private val api = ApiClient.retrofit.create(ApiService::class.java)

    // ---------- REGISTRAR USUARIO ----------
    suspend fun registerUser(
        name: String,
        email: String,
        password: String,
        telefono: String
    ): Boolean {

        val request = RegisterRequest(
            name = name,
            email = email,
            password = password,
            telefono = telefono
        )

        val response = api.registerUser(request)
        return response.isSuccessful
    }

    // ---------- LOGIN USUARIO ----------
    suspend fun loginUser(email: String, password: String): Boolean {
        val request = LoginRequest(email = email, password = password)

        return withContext(Dispatchers.IO) {
            val response = api.loginUser(request)
            response.isSuccessful
        }
    }
}

