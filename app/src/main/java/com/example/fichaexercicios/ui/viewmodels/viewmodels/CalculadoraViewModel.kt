package com.example.fichaexercicios.ui.viewmodels.viewmodels

import androidx.lifecycle.ViewModel
import com.example.fichaexercicios.data.remote.RetrofitBuilder
import com.example.fichaexercicios.ui.viewmodels.logic.OperationLogic

const val ENDPOINT = "https://cm-calculadora.herokuapp.com/api/"

class CalculadoraViewModel() : ViewModel() {

    private val operationLogic = OperationLogic(RetrofitBuilder.getInstance(ENDPOINT))

}