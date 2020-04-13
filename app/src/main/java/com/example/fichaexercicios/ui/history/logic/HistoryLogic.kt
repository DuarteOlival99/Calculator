package com.example.fichaexercicios.ui.history.logic

import com.example.fichaexercicios.data.models.Operation
import com.example.fichaexercicios.data.storage.ListStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder

class HistoryLogic {

    private val storage = ListStorage.getInstance()

    fun history () : List<Operation> {
        return storage.getAll()
    }

    fun delete(position: Int)  {
        storage.delete(position)
    }


}