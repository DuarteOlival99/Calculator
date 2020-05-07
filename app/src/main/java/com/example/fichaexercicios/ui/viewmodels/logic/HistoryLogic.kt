package com.example.fichaexercicios.ui.viewmodels.logic

import com.example.fichaexercicios.data.entity.Operation
import com.example.fichaexercicios.data.local.list.ListStorage
import com.example.fichaexercicios.data.remote.RetrofitBuilder
import com.example.fichaexercicios.data.remote.responses.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit



class HistoryLogic {

    private val storage = ListStorage.getInstance()
    //private val retrofit: Retrofit = RetrofitBuilder.getInstance(ENDPOINTHISTORY)
    //private val operation = OperationLogic(retrofit)

    fun history () : List<Operation>{
        return storage.getAll()
    }

//    fun listHistory() {
//        CoroutineScope(Dispatchers.IO).launch {
//            operation.getOperations(it)
//        }
//    }

    fun delete(position: Int)  {
        storage.delete(position)
    }

    fun getLogin(): LoginResponse {
        return storage.getToken()
    }


}