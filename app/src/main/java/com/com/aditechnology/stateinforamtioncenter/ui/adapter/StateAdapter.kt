package com.com.aditechnology.stateinforamtioncenter.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.com.aditechnology.stateinforamtioncenter.R
import com.com.aditechnology.stateinforamtioncenter.model.State

class StateAdapter ( private var states: MutableList<State>,private val listener: OnStateClickListener,) : RecyclerView.Adapter<StateAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.state_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (states.isNotEmpty()) {
            Log.e("TAG", "Adapter " + states[position])
            holder.bind(states[position])
        }
    }

    override fun getItemCount(): Int {
        return states.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val stateNameTextView: TextView = itemView.findViewById(R.id.state_name_text_view)
        init {
            itemView.setOnClickListener {
                listener.onStateClick(states[adapterPosition])
            }
        }

        fun bind(state: State) {
            stateNameTextView.text = state.state
        }

    }
    fun submitList(newStates: MutableList<State>) {
        states.clear()
        states =  newStates
        Log.e("TAG","submit list"+states.size)
        notifyDataSetChanged()
    }
    interface OnStateClickListener {
        fun onStateClick(state: State)
    }

}