package com.example.hellokitty20.data.model

data class UserResponse(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val telefono: String?,
    val edad: Int?
)