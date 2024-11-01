package com.com.aditechnology.stateinforamtioncenter.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.com.aditechnology.stateinforamtioncenter.R
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
            RECYCLER_VIEW_HOLDER -> RecyclerViewViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
            )
            else -> HeaderViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.header_layout, parent, false)
            )
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
    inner class RecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TownName) {
            itemView.findViewById<TextView>(R.id.town_name).text = item.townName
        }
    }
    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind( state: State?) {

            val stateNameTextView = itemView.findViewById<TextView>(R.id.state_name)
            val populationTextView = itemView.findViewById<TextView>(R.id.population)
            val countiesTextView = itemView.findViewById<TextView>(R.id.counties)
            val detailTextView = itemView.findViewById<TextView>(R.id.detail)

            val detailTextViewTitle = itemView.findViewById<TextView>(R.id.text_view_details)
            val countiesTextViewTitle = itemView.findViewById<TextView>(R.id.text_view_counties)
            val populationTextViewTitle = itemView.findViewById<TextView>(R.id.text_view_population)


            val emptyMessageTextView = itemView.findViewById<TextView>(R.id.empty_message)

            if (state != null && state.state.isNotEmpty()) {
                stateNameTextView.visibility = View.VISIBLE
                populationTextView.visibility = View.VISIBLE
                countiesTextView.visibility = View.VISIBLE
                detailTextView.visibility = View.VISIBLE
                detailTextViewTitle.visibility = View.VISIBLE
                countiesTextViewTitle.visibility = View.VISIBLE
                populationTextViewTitle.visibility = View.VISIBLE

                stateNameTextView.text = state.state
                populationTextView.text = state.population.toString()
                countiesTextView.text = state.counties.toString()
                detailTextView.text = state.detail
                emptyMessageTextView.visibility = View.GONE
            } else {
                stateNameTextView.visibility = View.GONE
                populationTextView.visibility = View.GONE
                countiesTextView.visibility = View.GONE
                detailTextView.visibility = View.GONE
                detailTextViewTitle.visibility = View.GONE
                countiesTextViewTitle.visibility = View.GONE
                populationTextViewTitle.visibility = View.GONE
                emptyMessageTextView.visibility = View.VISIBLE
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

