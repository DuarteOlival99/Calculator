package com.example.fichaexercicios.ui.viewmodels.viewmodels

import androidx.lifecycle.ViewModel
import com.example.fichaexercicios.data.entity.Operation
import com.example.fichaexercicios.data.remote.RetrofitBuilder
import com.example.fichaexercicios.data.remote.responses.LoginResponse
import com.example.fichaexercicios.data.remote.services.OperationService
import com.example.fichaexercicios.ui.listeners.OnGetListHistory
import com.example.fichaexercicios.ui.viewmodels.logic.HistoryLogic
import com.example.fichaexercicios.ui.listeners.OnHistoryChanged
import com.example.fichaexercicios.ui.viewmodels.logic.OperationLogic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

const val ENDPOINTHISTORY = "https://cm-calculadora.herokuapp.com/api/"

class HistoryViewModel : ViewModel(){
    //implementação da ViewModel
    private val historyLogic =
        HistoryLogic()
    var listHistory = mutableListOf<Operation>()
    private var listener: OnHistoryChanged? = null
    private var listenerList : OnGetListHistory? = null
    private val retrofit: Retrofit = RetrofitBuilder.getInstance(ENDPOINTHISTORY)
    private val operation = OperationLogic(retrofit)

    fun onGetListHistory(){
        CoroutineScope(Dispatchers.IO).launch {
            listenerList?.let { operation.getOperations(it) }
        }
    }

    fun registerListenerList(listener: OnGetListHistory){
        this.listenerList = listener
    }

    fun unregisterListenerList(){
        listenerList = null
    }


    fun getLogin() : LoginResponse {
        return historyLogic.getLogin()
    }

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