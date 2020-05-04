package com.example.fichaexercicios.ui.listeners

import com.example.fichaexercicios.data.entity.Operation

interface OnHistoryChanged {

    fun onHistoryChanged(list: MutableList<Operation>)

}