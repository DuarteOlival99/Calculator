package com.example.fichaexercicios.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_calculator.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat
import java.util.*
import butterknife.Optional;
import com.example.fichaexercicios.*
import com.example.fichaexercicios.viewModel.CalculatorViewModel
import kotlinx.android.synthetic.main.fragment_calculator.view.*

const val EXTRA_NAME = "name"
const val EXTRA_HISTORY = "history"

class CalculatorFragment : Fragment() {
    private lateinit var viewModel: CalculatorViewModel

    private val TAG = MainActivity::class.java.simpleName
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
        viewModel.display.let { view.text_visor.text = it }
        ButterKnife.bind(this, view)
        return view

        // Inflate the layout for this fragment

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list_historic?.layoutManager = LinearLayoutManager(activity as Context)
        list_historic?.adapter = HistoryAdapter(
            activity as Context,
            R.layout.item_expression,
            listHistory
        )


    }

    @OnClick (R.id.button_equals)
    fun onClickEquals(view: View){

        text_visor.text = viewModel.onClickEquals()

        /*
        val symbol = view.tag.toString()
        Log.i(TAG, "click no botão $symbol")
        var conta : String = text_visor.text.toString()
        val expression =
            ExpressionBuilder(text_visor.text.toString()).build()
        lastResult = expression.evaluate().toString()
        var teste : Operation =
            Operation(
                conta,
                expression.evaluate().toString()
            )
        listHistory.add(teste)
        text_visor.text = expression.evaluate().toString()

        list_historic?.layoutManager = LinearLayoutManager(activity as Context)
        list_historic?.adapter = HistoryAdapter(
            activity as Context,
            R.layout.item_expression,
            listHistory
        )

        Log.i(TAG, "list History = ${listHistory}")
        Log.i(TAG, "O resultado da expressão é ${text_visor.text}")
        Toast.makeText(activity as Context, "Hora atual: ${format.format(Date())}", Toast.LENGTH_SHORT).show()
        */
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

        text_visor.text = viewModel.onClickSymbol(view.tag.toString())

        /*
        val symbol = view.tag.toString()
        Log.i(TAG, "click no botão $symbol")
        if (text_visor.text == "0") {
            text_visor.text = symbol
        }else if (symbol == "CE" || symbol == "C"){
            text_visor.text = "0"
        }else{
            text_visor.append(symbol)
        }

         */
    }

}