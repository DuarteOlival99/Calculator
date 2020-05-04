package com.example.fichaexercicios.domain.auth.login.viewModel

import androidx.lifecycle.ViewModel
import com.example.fichaexercicios.data.entity.User
import com.example.fichaexercicios.domain.auth.login.logic.LoginLogic

class LoginViewModel : ViewModel() {

    private val userLogic =
        LoginLogic()
    var listUsers = mutableListOf<User>()

    fun getUsers() : MutableList<User> {
        return userLogic.user().toMutableList()
    }

    fun validaLogin(name: String, pass: String) : String{

        listUsers = getUsers()

        for (user in listUsers){
            if (user.name == name && user.pass == pass){
                return user.email
            }
        }
        return ""
    }


}