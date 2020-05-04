package com.example.fichaexercicios.domain.auth.login.logic

import com.example.fichaexercicios.data.entity.User
import com.example.fichaexercicios.data.local.list.ListStorage

class LoginLogic {

    private val storage = ListStorage.getInstance()

    fun user() : List<User> {
        return storage.getAllUsers()
    }

}