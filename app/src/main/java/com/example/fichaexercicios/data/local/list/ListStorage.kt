package com.example.fichaexercicios.data.local.list

import com.example.fichaexercicios.data.entity.Operation
import com.example.fichaexercicios.data.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListStorage private constructor() {

    private val storage = mutableListOf<Operation>()

    private val defaultUser = User("teste", "teste@gmail.com", "teste")
    private val users = mutableListOf<User>(defaultUser)

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


}