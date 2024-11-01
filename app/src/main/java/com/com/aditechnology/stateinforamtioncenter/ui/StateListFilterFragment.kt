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
import com.com.aditechnology.stateinforamtioncenter.ui.adapter.StateAdapter
import com.com.aditechnology.stateinforamtioncenter.viewmodel.SharedStateViewModel

class StateListFilterFragment  : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var stateAdapter: StateAdapter
    private val viewModel: SharedStateViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_state_llst_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        stateAdapter = StateAdapter(mutableListOf(),object : StateAdapter.OnStateClickListener {
            override fun onStateClick(state: State) {
                // Handle state click here
                viewModel.selectState(state)
                Log.d("StateFragment", "Clicked state: $state")
                // Navigate to next fragment or activity
            }
        })
        recyclerView.adapter = stateAdapter
        recyclerView.layoutManager = LinearLayoutManager(context) // Add layout manager

        // Observe states and update adapter
        viewModel.filteredStates.observe(viewLifecycleOwner) { states ->
            if (states!=null) {
                stateAdapter.submitList(states.toMutableList())
            }
            Log.e("TAG", "Adapter item count: ${stateAdapter.itemCount}") // Verify adapter update
        }
    }
}