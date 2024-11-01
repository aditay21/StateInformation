package com.com.aditechnology.stateinforamtioncenter.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.com.aditechnology.stateinforamtioncenter.R
import com.com.aditechnology.stateinforamtioncenter.databinding.HeaderLayoutBinding
import com.com.aditechnology.stateinforamtioncenter.databinding.RecyclerViewItemBinding
import com.com.aditechnology.stateinforamtioncenter.model.State
import com.com.aditechnology.stateinforamtioncenter.model.TownName


class StateDetailsAdapter (
    private val recyclerViewItems: MutableList<TownName>,
    private var headerItem: State?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    // Define view holder types
    companion object {
        const val RECYCLER_VIEW_HOLDER = 1
        const val HEADER_VIEW_HOLDER = 2
    }

    // Determine view holder type based on position
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER_VIEW_HOLDER else RECYCLER_VIEW_HOLDER
    }

    // Create view holders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RECYCLER_VIEW_HOLDER -> {
                val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RecyclerViewViewHolder(binding)
            }
            else -> {
                val binding = HeaderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
        }
    }
    // Bind data to view holders
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecyclerViewViewHolder -> {
                val item = recyclerViewItems[position - 1] // Adjust position for header
                holder.bind(item)
            }
            is HeaderViewHolder->{
                holder.bind(headerItem)
            }

        }
    }

    // Get item count (header + recycler view items)
    override fun getItemCount(): Int {
        return 1 + recyclerViewItems.size
    }

    // RecyclerViewViewHolder
    inner class RecyclerViewViewHolder(private val binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TownName) {
            itemView.findViewById<TextView>(R.id.town_name).text = item.townName
        }
    }
    inner class HeaderViewHolder(private val binding: HeaderLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind( state: State?) {

            if (state != null && state.state.isNotEmpty()) {
                binding.stateName.visibility = View.VISIBLE
                binding.population.visibility = View.VISIBLE
                binding.counties.visibility = View.VISIBLE

                binding.textViewDetails.visibility = View.VISIBLE
                binding.counties.visibility = View.VISIBLE

                binding.textViewCounties.visibility = View.VISIBLE
                binding.textViewPopulation.visibility = View.VISIBLE
                binding.emptyMessage.visibility = View.GONE
                binding.stateName.text = state.state
                binding.population.text = state.population.toString()
                binding.counties.text = state.counties.toString()
                binding.textViewDetails.text = state.detail

            } else {
                binding.stateName.visibility = View.GONE
                binding.population.visibility = View.GONE
                binding.counties.visibility = View.GONE

                binding.textViewDetails.visibility = View.GONE
                binding.counties.visibility = View.GONE

                binding.textViewCounties.visibility = View.GONE
                binding.textViewPopulation.visibility = View.GONE
                binding.emptyMessage.visibility = View.VISIBLE
            }
        }
    }

    fun updateDetails(item: MutableList<TownName>,
                      stateItem: State?) {
        recyclerViewItems.clear()
        recyclerViewItems.addAll(item)
        headerItem = stateItem
        notifyDataSetChanged()
    }
}

