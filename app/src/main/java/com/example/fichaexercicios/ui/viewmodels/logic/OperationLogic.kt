package com.example.fichaexercicios.ui.viewmodels.logic

import android.util.Log
import com.example.fichaexercicios.data.local.list.ListStorage
import com.example.fichaexercicios.data.remote.requests.Operations
import com.example.fichaexercicios.data.remote.responses.OperationListResponse
import com.example.fichaexercicios.data.remote.services.OperationService
import com.example.fichaexercicios.ui.listeners.OnGetListHistory
import com.example.fichaexercicios.ui.listeners.OnLoginTry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.util.*

class OperationLogic(private val retrofit: Retrofit) {

    private val storage = ListStorage.getInstance()
    private val token = storage.login.getToken()

    suspend fun postOperation(expression: String, result: Double){
        val service = retrofit.create(OperationService::class.java)
        //Log.i("teste", "$expression = $result")
        val response = service.addOperation(token, Operations(UUID.randomUUID().toString(), expression, result))
        CoroutineScope(Dispatchers.IO).launch {
            if(response.isSuccessful) {
                val message = response.body()

                Log.i("teste", message.toString())
                //list.onOperationPost(true)
            }else {
                //list.onOperationPost(false)
                Log.i("teste", response.body().toString())
            }
        }

    }

    suspend fun getOperations(list: OnGetListHistory){
        val service = retrofit.create(OperationService::class.java)
        val response = service.getOperation(token)
        CoroutineScope(Dispatchers.IO).launch {
            if ( response.isSuccessful){
                val lista = response.body()
                list.onGetListHistory(lista!!)
                Log.i("listaCerta", response.body().toString())
            }else{
                Log.i("listaErrada", response.body().toString())
            }
        }

    }

    suspend fun deleteOperations(){
        val service = retrofit.create(OperationService::class.java)
        val response = service.deleteOperation(token)
        CoroutineScope(Dispatchers.IO).launch {
            if ( response.isSuccessful){
                val mensagem = response.body()!!
                Log.i("delete", mensagem.toString())
            }else{
                Log.i("delete", response.body().toString())
            }
        }

    }


}