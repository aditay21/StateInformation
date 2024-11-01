package com.com.aditechnology.stateinforamtioncenter.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.com.aditechnology.stateinforamtioncenter.R
import com.com.aditechnology.stateinforamtioncenter.databinding.StateItemBinding
import com.com.aditechnology.stateinforamtioncenter.model.State

class StateAdapter ( private var states: MutableList<State>,private val listener: OnStateClickListener,) : RecyclerView.Adapter<StateAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: StateItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.state_item,
            parent,
            false
        )
        return ViewHolder(binding)
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

    inner class ViewHolder(private val binding: StateItemBinding) : RecyclerView.ViewHolder(binding.root) {
       // private val stateNameTextView: TextView = itemView.findViewById(R.id.state_name_text_view)
        init {
            itemView.setOnClickListener {
                listener.onStateClick(states[adapterPosition])
            }
        }

        fun bind(state: State) {
            binding.stateNameTextView.text = state.state
            binding.executePendingBindings()
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