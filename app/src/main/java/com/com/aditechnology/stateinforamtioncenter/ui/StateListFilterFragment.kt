package com.com.aditechnology.stateinforamtioncenter.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.com.aditechnology.stateinforamtioncenter.R
import com.com.aditechnology.stateinforamtioncenter.databinding.FragmentStateListScreenBinding
import com.com.aditechnology.stateinforamtioncenter.model.State
import com.com.aditechnology.stateinforamtioncenter.ui.adapter.StateAdapter
import com.com.aditechnology.stateinforamtioncenter.viewmodel.SharedStateViewModel

class StateListFilterFragment  : Fragment() {


    private lateinit var binding: FragmentStateListScreenBinding
    private lateinit var stateAdapter: StateAdapter
    private val viewModel: SharedStateViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_state_list_screen, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        stateAdapter = StateAdapter(mutableListOf(),object : StateAdapter.OnStateClickListener {
            override fun onStateClick(state: State) {
                // Handle state click here
                viewModel.selectState(state)
            // Navigate to next fragment or activity
            }
        })
        binding.recyclerView.adapter = stateAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context) // Add layout manager

        // Observe states and update adapter
        viewModel.filteredStates.observe(viewLifecycleOwner) { states ->
            if (states!=null) {
                stateAdapter.submitList(states.toMutableList())
            }
            Log.e("TAG", "Adapter item count: ${stateAdapter.itemCount}") // Verify adapter update
        }
    }
}