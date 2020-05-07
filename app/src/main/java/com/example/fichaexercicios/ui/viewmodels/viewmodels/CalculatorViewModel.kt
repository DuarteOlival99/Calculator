package com.example.fichaexercicios.ui.viewmodels.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.fichaexercicios.data.remote.RetrofitBuilder
import com.example.fichaexercicios.data.remote.responses.LoginResponse
import com.example.fichaexercicios.data.room.CalculatorDatabase
import com.example.fichaexercicios.domain.auth.login.logic.AuthLogic
import com.example.fichaexercicios.ui.viewmodels.logic.CalculatorLogic
import com.example.fichaexercicios.ui.listeners.OnDisplayChanged
import com.example.fichaexercicios.ui.listeners.OnLoginTry
import com.example.fichaexercicios.ui.listeners.OnOperationPost
import com.example.fichaexercicios.ui.viewmodels.logic.OperationLogic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class CalculatorViewModel(application: Application) : AndroidViewModel(application){

    private val storage = CalculatorDatabase.getInstance(application).operationDao()
    private val calculatorLogic = CalculatorLogic(storage)
    //implementação da ViewModel
//    private val calculatorLogic = CalculatorLogic()
    var display: String = ""
    private var listener: OnDisplayChanged? = null
    private var listenerOperation: OnOperationPost? = null
    //private val operationLogic = OperationLogic(RetrofitBuilder.getInstance(ENDPOINT))

    fun onClickOperation(uuid: String, expression: String, result: Double){
        CoroutineScope(Dispatchers.IO).launch {
            //listenerOperation?.let { operationLogic.doOperation(expression, result, it) }
        }
    }

    fun registerListenerOperation(listener: OnOperationPost){
        //this.listenerOperation = listenerOperation
    }

    fun unregisterListenerOperation(){
        listenerOperation = null
    }

    fun deleteOperations(){
        calculatorLogic.deleteOperations()
    }

    private fun notifyOnDisplayChanged(){
        listener?.onDisplayChanged(display)
    }

    fun registerListener(listener: OnDisplayChanged) {
        this.listener = listener
        listener.onDisplayChanged(display)
    }

    fun unregisterListener() {
        listener = null
    }

    fun onClickSymbol(symbol: String) {
        display = calculatorLogic.insertSymbol(display, symbol)
        notifyOnDisplayChanged()
    }

    fun onClickEquals() {
        display = calculatorLogic.performOperation(display).toString()
        notifyOnDisplayChanged()
    }

}