package com.example.fichaexercicios.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import com.example.fichaexercicios.*
import com.example.fichaexercicios.data.entity.Operation
import com.example.fichaexercicios.data.remote.requests.Operations
import com.example.fichaexercicios.domain.calculator.MainActivity
import com.example.fichaexercicios.ui.adapters.HistoryAdapter
import com.example.fichaexercicios.ui.listeners.OnGetListHistory
import com.example.fichaexercicios.ui.listeners.OnHistoryChanged
import com.example.fichaexercicios.ui.viewmodels.viewmodels.HistoryViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment(),
    OnHistoryChanged, OnGetListHistory {
    private val TAG = HistoryFragment::class.java.simpleName
    private lateinit var viewModel: HistoryViewModel
    private var listHistory = mutableListOf<Operation>()
    private var listH = listOf<Operations>()
    val lista = mutableListOf<Operation>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        viewModel.getHistory()
        viewModel.onGetListHistory()
        ButterKnife.bind(this, view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
    }

    override fun onStart() {
        viewModel.registerListener(this)
        viewModel.registerListenerList(this)
        super.onStart()
        //historico()
        //viewModel.onGetListHistory()
        historico()
    }

    override fun onHistoryChanged(list: MutableList<Operation>) {
        list?.let { listHistory = it }
        page_list_historic?.adapter =
            HistoryAdapter(
                viewModel,
                activity as Context,
                R.layout.item_expression,
                listHistory
            )
    }

    override fun onDestroy() {
        viewModel.unregisterListener()
        viewModel.unregisterListenerList()
        super.onDestroy()
    }

     fun historico() {
//         viewModel.getHistory()
         for (listaa in listH){
             val operacao = Operation(listaa.getExpression(), listaa.getResult())
             //listHistory.add(operacao)
             lista.add(operacao)
         }
         Log.i("listaH", listH.toString())
         Log.i("listaH", lista.toString())

         viewModel.onGetListHistory()
         page_list_historic?.layoutManager = LinearLayoutManager(activity as Context)
         Log.i(TAG, "Historic")
         page_list_historic?.adapter =
             HistoryAdapter(
                 viewModel,
                 activity as Context,
                 R.layout.item_expression,
                 lista
             )
         Log.i("TAG", lista.toString())
     }



    override fun onGetListHistory(list: List<Operations>) {
        list?.let { listH = it  }
        Log.i("lista",listH.toString())

    }



}