package com.example.fichaexercicios.ui.history.viewModel

import androidx.lifecycle.ViewModel
import com.example.fichaexercicios.data.models.Operation
import com.example.fichaexercicios.ui.history.logic.HistoryLogic

class HistoryViewModel : ViewModel(){
    //implementação da ViewModel
    private val historyLogic = HistoryLogic()
    var listHistory = mutableListOf<Operation>()

    fun getHistory() : List<Operation> {
        return historyLogic.history()
    }

    fun delete(position: Int)  {
        historyLogic.delete(position)
    }

}