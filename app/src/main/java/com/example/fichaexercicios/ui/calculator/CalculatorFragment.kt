package com.example.fichaexercicios.ui.calculator

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_calculator.*
import java.text.SimpleDateFormat
import butterknife.Optional;
import com.example.fichaexercicios.*
import com.example.fichaexercicios.data.models.Operation
import com.example.fichaexercicios.ui.calculator.observable.OnDisplayChanged
import com.example.fichaexercicios.ui.calculator.viewModel.CalculatorViewModel
import com.example.fichaexercicios.ui.history.HistoryAdapter
import com.example.fichaexercicios.ui.history.observable.OnHistoryChanged
import com.example.fichaexercicios.ui.history.viewModel.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_history.*

const val EXTRA_NAME = "name"
const val EXTRA_HISTORY = "history"

class CalculatorFragment : Fragment(), OnDisplayChanged, OnHistoryChanged {
    private lateinit var viewModel: CalculatorViewModel
    private lateinit var viewModelHistory: HistoryViewModel

    private val TAG = CalculatorFragment::class.java.simpleName
    private var lastResult = "0"
    private val VISOR_KEY = "visor"
    private var listHistory = mutableListOf<Operation>()
    @SuppressLint("SimpleDateFormat")
    var format: SimpleDateFormat = SimpleDateFormat("HH:mm:ss")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        viewModelHistory = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
        // Inflate the layout for this fragment
    }

    override fun onStart() {
        viewModel.registerListener(this)
        viewModelHistory.registerListener(this)
        super.onStart()
        historico()
    }

    override fun onDisplayChanged(value: String?){
        value?.let { text_visor.text = it }
    }

    override fun onHistoryChanged(list: MutableList<Operation>) {
        list?.let { listHistory = it }
    }

    override fun onDestroy() {
        viewModel.unregisterListener()
        viewModelHistory.unregisterListener()
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Optional()
    @OnClick ( R.id.button_00,
        R.id.button_22,
        R.id.button_ce,
        R.id.button_0,
        R.id.button_1,
        R.id.button_2,
        R.id.button_3,
        R.id.button_4,
        R.id.button_5,
        R.id.button_6,
        R.id.button_7,
        R.id.button_8,
        R.id.button_9,
        R.id.button_less,
        R.id.button_adition,
        R.id.button_multiplication,
        R.id.button_division,
        R.id.button_C,
        R.id.button_point
    )
    fun onClickSymbol(view: View) {
        viewModel.onClickSymbol(view.tag.toString())
    }

    @OnClick (R.id.button_equals)
    fun onClickEquals(view: View){
        viewModel.onClickEquals()
        viewModelHistory.getHistory()
        historico()
    }

    fun historico(){
        list_historic?.layoutManager = LinearLayoutManager(activity as Context)
        Log.i(TAG, "Historic")
        list_historic?.adapter = HistoryAdapter(
            activity as Context,
            R.layout.item_expression,
            listHistory
        )
        Log.i(TAG, listHistory.toString())
    }

}