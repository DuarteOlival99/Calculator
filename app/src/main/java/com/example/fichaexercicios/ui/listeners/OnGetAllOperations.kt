package com.example.fichaexercicios.ui.listeners

import com.example.fichaexercicios.data.entity.Operation

interface OnGetAllOperations {

    fun onGetAllOperations(list: MutableList<Operation>)

}