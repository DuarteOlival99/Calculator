package com.example.fichaexercicios.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_expression.view.*

class HistoryAdapter(private val context: Context, private val layout: Int, private val
items: MutableList<Operation>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val expression: TextView = view.text_expression
        val result: TextView = view.text_result

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            HistoryViewHolder {
        return HistoryViewHolder(
            LayoutInflater.from(context).inflate(layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.expression.text = items[position].exprexion
        holder.result.text = items[position].result.toString()
    }

    override fun getItemCount() = items.size



}
