package com.example.fichaexercicios.ui.login.viewModel

import android.content.SyncInfo
import androidx.lifecycle.ViewModel
import com.example.fichaexercicios.data.models.User
import com.example.fichaexercicios.ui.login.logic.LoginLogic

class LoginViewModel : ViewModel() {

    private val userLogic = LoginLogic()
    var listUsers = mutableListOf<User>()

    fun getUsers() : MutableList<User> {
        listUsers = userLogic.user().toMutableList()
        return listUsers
    }

    fun validaLogin(name: String, pass: String) : String{

        getUsers()

        for (user in listUsers){
            if (user.name == name && user.pass == pass){
                return user.email
            }
        }
        return ""
    }


}