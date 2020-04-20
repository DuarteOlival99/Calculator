package com.example.fichaexercicios.ui.register.logic

import com.example.fichaexercicios.data.models.Operation
import com.example.fichaexercicios.data.models.User
import com.example.fichaexercicios.data.storage.ListStorage
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