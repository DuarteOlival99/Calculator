package com.example.fichaexercicios.domain.auth.register.viewModel

import androidx.lifecycle.ViewModel
import com.example.fichaexercicios.data.entity.User
import com.example.fichaexercicios.domain.auth.register.logic.RegisterLogic

class RegisterViewModel : ViewModel() {

    private val userLogic =
        RegisterLogic()
    var listUsers = mutableListOf<User>()

    fun getUsers() : MutableList<User> {
        //listUsers = userLogic.user().toMutableList()
        return listUsers
    }

    fun newUser(user: User){
        userLogic.performOperation(user)
    }

    fun validaUserList(nome: String): Boolean {
        for (user in listUsers){
            if (user.name == nome){
                return false
            }
        }
        return true
    }

    fun validaPass(pass: String, passConfirm: String) : Boolean {
        return pass == passConfirm
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validaEmail(email: String) : Boolean {
        return isEmailValid(email)
    }

    fun validaNome(nome: String) : Boolean {
        return nome.isNotEmpty()
    }

}