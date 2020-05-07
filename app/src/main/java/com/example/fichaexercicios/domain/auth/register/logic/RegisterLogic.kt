package com.example.fichaexercicios.domain.auth.register.logic

import android.util.Log
import com.example.fichaexercicios.data.entity.User
import com.example.fichaexercicios.data.local.list.ListStorage
import com.example.fichaexercicios.data.remote.RetrofitBuilder
import com.example.fichaexercicios.data.remote.requests.Login
import com.example.fichaexercicios.data.remote.requests.Register
import com.example.fichaexercicios.data.remote.services.AuthService
import com.example.fichaexercicios.data.remote.services.RegisterService
import com.example.fichaexercicios.domain.auth.register.viewModel.ENDPOINT
import com.example.fichaexercicios.ui.listeners.OnLoginTry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

const val ENDPOINTTT = "https://cm-calculadora.herokuapp.com/api/"

class RegisterLogic {

    private val storage = ListStorage.getInstance()
    private val retrofit = RetrofitBuilder.getInstance(ENDPOINTTT)

    suspend fun RegisterUser(name: String, email: String, password: String) {
        val service = retrofit.create(RegisterService::class.java)
        val response = service.register(Register(name, email, password))
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("register", name + " " + email + " " + password)
            if (response.isSuccessful){
                //temos acesso ao objecto de LoginResponde atraves do metodo

                val token = response.body()
                Log.i("Resposta", token.toString())
                val user = User(name, email, password)
                storage.insertUser(user)
            }else{
                // erro de autenticacao
                val a = response.body()
                Log.i("Erro_Resposta", response.errorBody().toString())
            }

        }


    }


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