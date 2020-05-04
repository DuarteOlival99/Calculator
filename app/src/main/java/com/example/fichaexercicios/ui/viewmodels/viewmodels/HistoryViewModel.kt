package com.example.fichaexercicios.ui.viewmodels.viewmodels

import androidx.lifecycle.ViewModel
import com.example.fichaexercicios.data.entity.Operation
import com.example.fichaexercicios.ui.viewmodels.logic.HistoryLogic
import com.example.fichaexercicios.ui.listeners.OnHistoryChanged

class HistoryViewModel : ViewModel(){
    //implementação da ViewModel
    private val historyLogic =
        HistoryLogic()
    var listHistory = mutableListOf<Operation>()
    private var listener: OnHistoryChanged? = null


    //Observable De ...
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
    //Observable ...ate aqui

    fun getHistory() {
        listHistory = historyLogic.history().toMutableList()
        notifyOnHistoryChanged() //chama o Observable
    }

    fun delete(position: Int)  {
        historyLogic.delete(position)
    }

}