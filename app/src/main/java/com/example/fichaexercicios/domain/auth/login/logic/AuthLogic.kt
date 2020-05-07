package com.example.fichaexercicios.domain.auth.login.logic

import android.util.Log
import com.example.fichaexercicios.data.local.list.ListStorage
import com.example.fichaexercicios.data.remote.requests.Login
import com.example.fichaexercicios.data.remote.responses.LoginResponse
import com.example.fichaexercicios.data.remote.services.AuthService
import com.example.fichaexercicios.ui.listeners.OnLoginTry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class AuthLogic (private val retrofit: Retrofit) {

    private val storage = ListStorage.getInstance()

    //vamos omitir a assinatura do metodo assim como o uso da coroutine
    suspend fun authenticateUser(email: String, password: String, list: OnLoginTry) {
        val service = retrofit.create(AuthService::class.java)
        val response = service.login(Login(email, password))
       CoroutineScope(Dispatchers.IO).launch {
        Log.i("auth", email + " " + password)
            if (response.isSuccessful){
                //temos acesso ao objecto de LoginResponde atraves do metodo
                // responde.body()

//                val login : LoginResponse = response.body()!!
//                storage.insertLogin(login)
//                Log.i("resposta", login.toString())

                val token = response.body()
                storage.insertLogin(token!!)
                list.onLoginTry(true)
            }else{
                // erro de autenticacao
//                val a = response.body()
//                Log.i("Erro_Resposta", response.errorBody() .toString())
                list.onLoginTry(false)
            }

        }


    }


}