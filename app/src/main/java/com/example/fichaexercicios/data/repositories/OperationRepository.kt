package com.example.fichaexercicios.data.repositories

import android.util.Log
import com.example.fichaexercicios.data.entity.Operation
import com.example.fichaexercicios.data.local.list.ListStorage
import com.example.fichaexercicios.data.remote.requests.Operations
import com.example.fichaexercicios.data.remote.services.OperationService
import com.example.fichaexercicios.data.room.dao.OperationDao
import com.example.fichaexercicios.ui.viewmodels.logic.OperationLogic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import retrofit2.Retrofit
import java.lang.Exception
import java.util.*

class OperationRepository(private val local: OperationDao, private val remote: Retrofit) {

    private val storage = ListStorage.getInstance()
    private val token = storage.login.getToken()

    suspend fun postOperation(expression: String, result: Double){
        val service = remote.create(OperationService::class.java)
        //Log.i("teste", "$expression = $result")
        val response = service.addOperation(token, Operations(UUID.randomUUID().toString(), expression, result))
        CoroutineScope(Dispatchers.IO).launch {
            if(response.isSuccessful) {
                val message = response.body()

                Log.i("teste", message.toString())
                //list.onOperationPost(true)
                
                //list.onOperationPost(false)
                Log.i("teste", response.body().toString())
            }
        }

    }


    fun performOperation(expression: String) {

        val expressionBuilder = ExpressionBuilder(expression).build()
        val result = expressionBuilder.evaluate()
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("teste", "$expression =$result")

            try {
                postOperation(expression, result)
            }catch (message: Exception){
                local.insert(
                    Operation(
                        expression,
                        result
                    )
                )
            }

        }
    }

}