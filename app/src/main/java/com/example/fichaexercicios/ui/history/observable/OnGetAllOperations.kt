package com.example.fichaexercicios.ui.history.observable

import com.example.fichaexercicios.data.models.Operation

interface OnGetAllOperations {

    fun onGetAllOperations(list: MutableList<Operation>)

}