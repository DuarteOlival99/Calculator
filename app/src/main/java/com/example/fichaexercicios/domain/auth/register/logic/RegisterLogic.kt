package com.example.fichaexercicios.domain.auth.register.logic

import com.example.fichaexercicios.data.entity.User
import com.example.fichaexercicios.data.local.list.ListStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterLogic {

    private val storage = ListStorage.getInstance()

    fun user() : List<User> {
        return storage.getAllUsers()
    }

    fun performOperation(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            storage.insertUser(
                user
            )
        }
    }

}