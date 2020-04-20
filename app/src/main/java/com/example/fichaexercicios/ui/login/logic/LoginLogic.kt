package com.example.fichaexercicios.ui.login.logic

import com.example.fichaexercicios.data.models.User
import com.example.fichaexercicios.data.storage.ListStorage

class LoginLogic {

    private val storage = ListStorage.getInstance()

    fun user() : List<User> {
        return storage.getAllUsers()
    }

}