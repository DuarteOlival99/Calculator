package com.example.fichaexercicios.ui.viewmodels.logic

import android.util.Log
import com.example.fichaexercicios.data.entity.Operation
import com.example.fichaexercicios.data.local.list.ListStorage
import com.example.fichaexercicios.data.remote.RetrofitBuilder
import com.example.fichaexercicios.data.remote.requests.Operations
import com.example.fichaexercicios.data.remote.services.OperationService
import com.example.fichaexercicios.data.repositories.OperationRepository
import com.example.fichaexercicios.data.room.dao.OperationDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import retrofit2.Retrofit
import java.util.*

const val ENDPOINT = "https://cm-calculadora.herokuapp.com/api/"

class CalculatorLogic(private val repository: OperationRepository) {


    private val retrofit: Retrofit = RetrofitBuilder.getInstance(ENDPOINT)
    private val operation = OperationLogic(retrofit)
    private val storage = ListStorage.getInstance()



    fun insertSymbol(display: String, symbol: String) : String {
         if(display.isEmpty() && symbol == "0") {
            return symbol
        }  else if(symbol == "C"){
             return "0"
         }else {
          return display + symbol
         }
    }

    fun performOperation (expression: String) : Double {

        val expressionBuilder = ExpressionBuilder(expression).build()
        val result = expressionBuilder.evaluate()
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("teste", "$expression =$result")
           repository.performOperation(expression)

        }
        return result
    }

    fun deleteOperations() {
        CoroutineScope(Dispatchers.IO).launch {
            operation.deleteOperations()
        }
    }

    fun getToken(): String? {
        return storage.getTokenShared()
    }


}