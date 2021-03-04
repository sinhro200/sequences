package com.sinhro.sequences.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sinhro.sequences.R
import com.sinhro.sequences.model.ISequenceGenerator

class ScrollableSequenceAdapter constructor(
    sequenceGenerator: ISequenceGenerator
) : RecyclerView.Adapter<ScrollableSequenceAdapter.CustomViewHolder>() {
    var sequenceGenerator: ISequenceGenerator = sequenceGenerator
        set(value) {
            field = value
            value.onNewValuesReady { newValues ->
                val from = sequence.size
                sequence.addAll(newValues)
                notifyItemRangeInserted(from, newValues.size)
            }
            refreshData()
        }
    init {
        sequenceGenerator.onNewValuesReady { newValues ->
            val from = sequence.size
            sequence.addAll(newValues)
            notifyItemRangeInserted(from, newValues.size)
        }
    }
    private var sequence: MutableList<String> = sequenceGenerator.getAll().toMutableList()

    fun refreshData(){
        sequence = sequenceGenerator.getAll().toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.element_view, parent, false)

        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.textView.text = sequence[position]
        if (position%4==0 || position%4==3)
            holder.itemView.setBackgroundResource(R.color.colorGray)
        else
            holder.itemView.setBackgroundResource(R.color.colorWhite)
    }

    override fun getItemCount(): Int {
        return sequence.size
    }

    override fun onViewAttachedToWindow(holder: CustomViewHolder) {
        super.onViewAttachedToWindow(holder)
        if (itemCount - holder.layoutPosition < 2)
            sequenceGenerator.generateNext(4)
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.element_text_view)
    }
}