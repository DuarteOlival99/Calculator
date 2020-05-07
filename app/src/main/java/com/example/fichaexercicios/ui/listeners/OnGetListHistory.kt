package com.example.fichaexercicios.ui.listeners

import com.example.fichaexercicios.data.remote.requests.Operations

interface OnGetListHistory {

    fun onGetListHistory(list: List<Operations>)

}