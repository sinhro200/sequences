package com.sinhro.sequences.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sinhro.sequences.R
import com.sinhro.sequences.model.ISequenceGenerator

class ScrollableSequenceAdapter constructor(
    initialSequenceGenerator: ISequenceGenerator
) : RecyclerView.Adapter<ScrollableSequenceAdapter.CustomViewHolder>() {

    private var countToGenerate = 8
    private var maxOffsetToUpdate = 20
    private var offsetToUpdate = 6

    var sequenceGenerator: ISequenceGenerator = initialSequenceGenerator
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
        initialSequenceGenerator.onNewValuesReady { newValues ->
            val from = sequence.size
            sequence.addAll(newValues)
            notifyItemRangeInserted(from, newValues.size)
        }
    }
    private var sequence: MutableList<String> = initialSequenceGenerator.getAll().toMutableList()

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
        val curOffset = itemCount - holder.layoutPosition
        if (curOffset < 2 && offsetToUpdate < maxOffsetToUpdate) {
            offsetToUpdate++
            Log.i("Offset to update","Increased. Current $offsetToUpdate")
        }
        if (curOffset < offsetToUpdate)
            sequenceGenerator.generateNext(countToGenerate)
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.element_text_view)
    }
}