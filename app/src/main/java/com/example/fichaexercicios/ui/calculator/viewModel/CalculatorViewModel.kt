package com.example.fichaexercicios.ui.calculator.viewModel

import androidx.lifecycle.ViewModel
import com.example.fichaexercicios.data.models.Operation
import com.example.fichaexercicios.ui.calculator.logic.CalculatorLogic
import com.example.fichaexercicios.ui.calculator.observable.OnDisplayChanged

class CalculatorViewModel : ViewModel(){
    //implementação da ViewModel
    private val calculatorLogic = CalculatorLogic()
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