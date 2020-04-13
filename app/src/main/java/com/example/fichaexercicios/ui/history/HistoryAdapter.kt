package com.example.fichaexercicios.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import butterknife.OnLongClick
import com.example.fichaexercicios.data.models.Operation
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

        val info: Operation = items[position]

        holder.itemView.setOnClickListener{
            Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
        }

        holder.itemView.setOnLongClickListener{
            Toast.makeText(context, "Operacao removida", Toast.LENGTH_SHORT).show()
            removeItem(info)
            return@setOnLongClickListener true
        }

    }

    private fun removeItem(info: Operation) {
        val position = items.indexOf(info)
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount() = items.size
}
