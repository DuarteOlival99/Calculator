package com.example.fichaexercicios.domain.auth.login.logic

import com.example.fichaexercicios.data.entity.User
import com.example.fichaexercicios.data.local.list.ListStorage
import com.example.fichaexercicios.data.remote.responses.LoginResponse

class LoginLogic {

    private val storage = ListStorage.getInstance()

    fun user() : List<User> {
        return storage.getAllUsers()
    }

    fun validaToken(): Boolean {

        return storage.validaToken()

    }

    fun getToken(): LoginResponse {
            return storage.getToken()
    }

}