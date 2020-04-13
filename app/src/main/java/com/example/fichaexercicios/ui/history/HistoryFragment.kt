package com.example.fichaexercicios.fragment

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
import com.example.fichaexercicios.*
import com.example.fichaexercicios.observable.OnDisplayChanged
import com.example.fichaexercicios.ui.HistoryAdapter
import com.example.fichaexercicios.ui.Operation
import com.example.fichaexercicios.viewModel.CalculatorViewModel
import kotlinx.android.synthetic.main.fragment_calculator.*

class HistoryFragment : Fragment(), OnDisplayChanged {
    private lateinit var viewModel: CalculatorViewModel
    private val TAG = HistoryFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        ButterKnife.bind(this, view)
        Log.i(TAG, "1")
        history()
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "2")

    }

    override fun onStart() {
        viewModel.registerListener(this)
        super.onStart()
    }

    override fun onDisplayChanged(value: String?){
        //value?.let { text_visor.text = it }
    }

    override fun onDestroy() {
        viewModel.unregisterListener()
        super.onDestroy()
    }

    private fun history(){
        Log.i(TAG, "3")
        val listHistory : List<Operation> = viewModel.getHistory()
        list_historic?.layoutManager = LinearLayoutManager(activity as Context)
        list_historic?.adapter = HistoryAdapter(
            activity as Context,
            R.layout.item_expression,
            listHistory.toMutableList()
        )
    }

}
