package com.example.fichaexercicios.ui.history.viewModel

import androidx.lifecycle.ViewModel
import com.example.fichaexercicios.data.models.Operation
import com.example.fichaexercicios.ui.calculator.observable.OnDisplayChanged
import com.example.fichaexercicios.ui.history.logic.HistoryLogic
import com.example.fichaexercicios.ui.history.observable.OnHistoryChanged

class HistoryViewModel : ViewModel(){
    //implementação da ViewModel
    private val historyLogic = HistoryLogic()
    var listHistory = mutableListOf<Operation>()
    private var listener: OnHistoryChanged? = null

    private fun notifyOnHistoryChanged(){
        listener?.onHistoryChanged(listHistory)
    }

    fun registerListener(listener: OnHistoryChanged) {
        this.listener = listener
        listener.onHistoryChanged(listHistory)
    }

    fun unregisterListener() {
        listener = null
    }

    fun getHistory() {
        listHistory = historyLogic.history().toMutableList()
        notifyOnHistoryChanged()
    }

    fun History(): MutableList<Operation> {
        return listHistory
    }

    fun delete(position: Int)  {
        historyLogic.delete(position)
    }

}