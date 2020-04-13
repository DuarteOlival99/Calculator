package com.example.fichaexercicios.ui.history.observable

import com.example.fichaexercicios.data.models.Operation

interface OnHistoryChanged {

    fun onHistoryChanged(list: MutableList<Operation>)

}