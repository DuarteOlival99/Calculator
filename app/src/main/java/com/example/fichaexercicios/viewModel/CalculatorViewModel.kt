package com.example.fichaexercicios.viewModel

import androidx.lifecycle.ViewModel
import com.example.fichaexercicios.logic.CalculatorLogic

class CalculatorViewModel : ViewModel(){
    //implementação da ViewModel
    private val calculatorLogic = CalculatorLogic()
    var display: String = ""



    fun onClickSymbol(symbol: String) : String {
        display = calculatorLogic.insertSymbol(display, symbol)
        return display
    }

    suspend fun onClickEquals() : String {
        val result = calculatorLogic.performOperation(display)
        display = result.toString()
        return display
    }

}