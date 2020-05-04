package com.example.fichaexercicios.ui.viewmodels.logic

import com.example.fichaexercicios.data.entity.Operation
import com.example.fichaexercicios.data.local.list.ListStorage

class HistoryLogic {

    private val storage = ListStorage.getInstance()

    fun history () : List<Operation>{
            return storage.getAll()
    }

    fun delete(position: Int)  {
        storage.delete(position)
    }



}