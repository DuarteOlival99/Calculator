package com.example.fichaexercicios.ui.viewmodels.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.fichaexercicios.data.room.CalculatorDatabase
import com.example.fichaexercicios.ui.viewmodels.logic.CalculatorLogic
import com.example.fichaexercicios.ui.listeners.OnDisplayChanged

class CalculatorViewModel(application: Application) : AndroidViewModel(application){

    private val storage = CalculatorDatabase.getInstance(application).operationDao()
    private val calculatorLogic = CalculatorLogic(storage)
    //implementação da ViewModel
//    private val calculatorLogic = CalculatorLogic()
    var display: String = ""
    private var listener: OnDisplayChanged? = null

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