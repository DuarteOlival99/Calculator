package com.example.fichaexercicios.domain.auth.login.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fichaexercicios.data.entity.User
import com.example.fichaexercicios.data.remote.RetrofitBuilder
import com.example.fichaexercicios.data.remote.responses.LoginResponse
import com.example.fichaexercicios.domain.auth.login.logic.AuthLogic
import com.example.fichaexercicios.domain.auth.login.logic.LoginLogic
import com.example.fichaexercicios.ui.listeners.OnLoginTry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val ENDPOINT = "https://cm-calculadora.herokuapp.com/api/"

class LoginViewModel() : ViewModel() {

    private val userLogic = LoginLogic()
    var listUsers = mutableListOf<User>()

    //vamos usar a mesma abordagem que utilizamos no Room
    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(ENDPOINT))
    private var listener : OnLoginTry? = null

    fun onClickLogin(nome: String, pass: String){
        CoroutineScope(Dispatchers.IO).launch {
            listener?.let { authLogic.authenticateUser(nome, pass, it) }
        }
    }

    fun registerListener(listener: OnLoginTry){
        this.listener = listener
    }

    fun unregisterListener(){
        listener = null
    }

//    suspend fun valida (email: String, pass: String) {
//        Log.i("auth", email + " " + pass)
//        authLogic.authenticateUser(email, pass)
//    }

    fun getToken() : LoginResponse{
       return userLogic.getToken()
    }


    fun getUsers() : MutableList<User> {
        return userLogic.user().toMutableList()
    }

    fun validaLogin(name: String, pass: String) : String{

        listUsers = getUsers()

        for (user in listUsers){
            Log.i("Login", user.toString())
            if (user.name == name && user.pass == pass){
                return user.email
            }
        }
        return ""
    }

    fun validaLoginToken(): Boolean {

        return userLogic.validaToken()

    }


}