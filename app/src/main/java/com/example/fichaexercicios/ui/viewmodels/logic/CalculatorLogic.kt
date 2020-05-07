package com.example.fichaexercicios.ui.viewmodels.logic

import android.util.Log
import com.example.fichaexercicios.data.entity.Operation
import com.example.fichaexercicios.data.local.list.ListStorage
import com.example.fichaexercicios.data.remote.RetrofitBuilder
import com.example.fichaexercicios.data.remote.requests.Operations
import com.example.fichaexercicios.data.remote.services.OperationService
import com.example.fichaexercicios.data.room.dao.OperationDao
import com.example.fichaexercicios.ui.viewmodels.viewmodels.ENDPOINT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import retrofit2.Retrofit
import java.util.*

class CalculatorLogic(private val storage: OperationDao) {

    private val retrofit: Retrofit = RetrofitBuilder.getInstance(ENDPOINT)
    private val operation = OperationLogic(retrofit)
    private val storagee = ListStorage.getInstance()



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
            operation.postOperation(expression, result)
            storage.insert(
                Operation(
                    expression,
                    result
                )
            )

        }
        return result
    }

    fun deleteOperations() {
        CoroutineScope(Dispatchers.IO).launch {
            operation.deleteOperations()
        }
    }


}