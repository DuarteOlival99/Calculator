package com.example.fichaexercicios.ui.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.fichaexercicios.*
import com.example.fichaexercicios.data.models.Operation
import com.example.fichaexercicios.ui.history.viewModel.HistoryViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.item_expression.*

class HistoryFragment : Fragment() {
    private val TAG = HistoryFragment::class.java.simpleName
    private lateinit var viewModel: HistoryViewModel
    private var listHistory = mutableListOf<Operation>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")

    }

    override fun onStart() {
        super.onStart()
        historico()

        page_list_historic.setOnClickListener{
            Toast.makeText(activity as Context, "teste", Toast.LENGTH_SHORT).show()
        }

    }

     fun historico(){
         listHistory = viewModel.getHistory().toMutableList()
         page_list_historic?.layoutManager = LinearLayoutManager(activity as Context)
         Log.i(TAG, "Historic")
         page_list_historic?.adapter = HistoryAdapter(
             activity as Context,
             R.layout.item_expression,
             listHistory
         )
         Log.i(TAG, listHistory.toString())
     }

    fun removeItem(position: Int){

        viewModel.delete(position)
        historico()
    }


}
