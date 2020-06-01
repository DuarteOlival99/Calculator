package com.example.fichaexercicios.data.local.list

import android.location.Location
import android.util.Log
import com.example.fichaexercicios.data.entity.Operation
import com.example.fichaexercicios.data.entity.User
import com.example.fichaexercicios.data.remote.requests.Operations
import com.example.fichaexercicios.data.remote.responses.LoginResponse
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListStorage private constructor() {

    private val storage = mutableListOf<Operation>()
    private var storageList = mutableListOf<Operations>()
    private var locationFinal : LocationResult? = null

//    private val defaultUser = User("teste", "teste@gmail.com", DigestUtils.sha256Hex("teste"))
    private val users = mutableListOf<User>()
    var login = LoginResponse("", "")

    companion object {

        private var instance: ListStorage? = null

        fun getInstance() : ListStorage {
            synchronized(this) {
                if(instance == null) {
                    instance =
                        ListStorage()
                }
                return instance as ListStorage
            }
        }

    }

    fun atualizaLocalizacao(location: LocationResult?){
        locationFinal = location
    }

    fun getLocalizacao(): LocationResult? {
        return locationFinal
    }

    fun atualizaOperations(list: List<Operations>){
            storageList = list.toMutableList()
    }

    fun getListOperations() : List<Operations>{
        return storageList
    }

    fun insertLogin(loginResponse : LoginResponse){
        login = loginResponse
    }

    suspend fun insert(operation: Operation){
        withContext(Dispatchers.IO){
            //Thread.sleep(30000)
            storage.add(operation)
        }
    }

    suspend fun insertUser(user: User){
        withContext(Dispatchers.IO){
            users.add(user)
        }
    }

    fun getAllUsers() : List<User>{
        return users.toList()
    }

    fun getAll() : List<Operation>{
       return storage.toList()
    }

    fun delete(position: Int)  {
       storage.removeAt(position)
    }

    fun validaToken(): Boolean {
        Log.i("valida", login.validaLogin().toString())
       return login.validaLogin()

    }

    fun getToken(): LoginResponse {
        return login
    }

    fun getTokenShared(): String? {
        return login.getToken()
    }


}