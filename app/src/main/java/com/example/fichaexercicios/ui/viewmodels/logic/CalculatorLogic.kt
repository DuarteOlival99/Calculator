package com.example.fichaexercicios.ui.viewmodels.logic

import com.example.fichaexercicios.data.entity.Operation
import com.example.fichaexercicios.data.local.list.ListStorage
import com.example.fichaexercicios.data.room.dao.OperationDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorLogic(private val storage: OperationDao) {

    //private val storage = ListStorage.getInstance()

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
            storage.insert(
                Operation(
                    expression,
                    result
                )
            )
        }
        return result
    }



}