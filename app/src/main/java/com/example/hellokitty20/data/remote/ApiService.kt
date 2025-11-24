package com.example.hellokitty20.data.remote

import com.example.hellokitty20.data.model.LoginRequest
import com.example.hellokitty20.data.model.RegisterRequest
import com.example.hellokitty20.data.model.UpdateUserRequest
import com.example.hellokitty20.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // LISTAR usuarios
    @GET("user")
    suspend fun getUsers(): Response<List<UserResponse>>

    // REGISTRAR usuario
    @POST("user")
    suspend fun registerUser(@Body request: RegisterRequest): Response<UserResponse>

    // LOGIN
    @POST("auth/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<UserResponse>


    // OBTENER usuario por ID
    @GET("user/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<UserResponse>

    // ACTUALIZAR usuario
    @PUT("user/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body request: UpdateUserRequest
    ): Response<UserResponse>

    // ELIMINAR usuario
    @DELETE("user/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}