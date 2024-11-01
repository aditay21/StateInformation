package com.com.aditechnology.stateinforamtioncenter.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.com.aditechnology.stateinforamtioncenter.R
import com.com.aditechnology.stateinforamtioncenter.model.State
import com.com.aditechnology.stateinforamtioncenter.ui.adapter.StateDetailsAdapter
import com.com.aditechnology.stateinforamtioncenter.viewmodel.SharedStateViewModel


class StateDetailScreenFragment : Fragment() {
    private lateinit var stateDetailsAdapter: StateDetailsAdapter
    private val TAG = StateDetailScreenFragment::class.java.name
    private val viewModel: SharedStateViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_state_details_screen, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "OnViewCreated")

        recyclerView = view.findViewById(R.id.recycler_view)

        stateDetailsAdapter =StateDetailsAdapter(mutableListOf(),State("",0,0,"", mutableListOf()))
        recyclerView.adapter =  stateDetailsAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.selectedState.observe(viewLifecycleOwner) { states ->
            Log.d(TAG, "Observer called with states: $states")
            states?.let {
                stateDetailsAdapter.updateDetails(states.townName,states)

             /*   stateDetailsAdapter =StateDetailsAdapter(states.townName,states)
                recyclerView.adapter =  stateDetailsAdapter
                recyclerView.layoutManager = LinearLayoutManager(context)*/
            }?: run {
                // This block executes if states is null
                // Handle the case when states is null, e.g., clear the adapter or show a message
                stateDetailsAdapter.updateDetails(mutableListOf(),State("",0,0,"", mutableListOf())) // Example function to clear details in the adapter
                // Optionally, you could show a message or an empty view
                //showEmptyStateMessage() // Replace with your actual implementation
            }
        }
    }
}