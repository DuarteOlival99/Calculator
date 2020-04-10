package com.example.fichaexercicios.viewModel

import androidx.lifecycle.ViewModel
import com.example.fichaexercicios.fragment.CalculatorFragment
import com.example.fichaexercicios.logic.CalculatorLogic
import com.example.fichaexercicios.observable.OnDisplayChanged

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