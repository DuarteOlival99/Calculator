package com.example.fichaexercicios.data.storage

import com.example.fichaexercicios.data.models.Operation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListStorage private constructor() {

    private val storage = mutableListOf<Operation>()

    companion object {

        private var instance: ListStorage? = null

        fun getInstance() : ListStorage {
            synchronized(this) {
                if(instance == null) {
                    instance = ListStorage()
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

    fun getAll() : List<Operation> {
        return storage.toList()
    }

    fun delete(position: Int)  {
       storage.removeAt(position)
    }


}