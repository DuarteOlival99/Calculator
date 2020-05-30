package com.example.fichaexercicios.data.remote.responses

data class  LoginResponse (private val email: String, private val token: String) {


    fun validaLogin(): Boolean {
        return !(email == "" && token == "")
    }

    fun getToken() : String{
        return token
    }

    override fun toString(): String {
        return "LoginResponse(email='$email', token='$token')"
    }

}