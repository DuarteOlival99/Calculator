package com.example.fichaexercicios.data.remote.services

import com.example.fichaexercicios.data.remote.requests.Login
import com.example.fichaexercicios.data.remote.requests.Register
import com.example.fichaexercicios.data.remote.responses.LoginResponse
import com.example.fichaexercicios.data.remote.responses.RegisterResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body

interface AuthService {

    @POST("users/login")
    suspend fun login(@Body body: Login): Response<LoginResponse>
}


