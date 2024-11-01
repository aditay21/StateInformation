package com.com.aditechnology.stateinforamtioncenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.com.aditechnology.stateinforamtioncenter.model.State
import com.com.aditechnology.stateinforamtioncenter.repositary.StateRepository
import com.com.aditechnology.stateinforamtioncenter.ui.StateDetailScreenFragment
import kotlinx.coroutines.launch

class SharedStateViewModel(private val repository: StateRepository) : ViewModel() {
    private val TAG = SharedStateViewModel::class.java.name
    private val _states = MutableLiveData<List<State>>()
    val states: LiveData<List<State>> = _states

    private val _selectedState = MutableLiveData<State?>()
    val selectedState: MutableLiveData<State?> = _selectedState

    private val _filteredStates = MutableLiveData<List<State>>()
    val filteredStates: LiveData<List<State>> = _filteredStates


    fun getStates() {
        viewModelScope.launch {
            try {
                val stateList = repository.getStates()
                _states.postValue(stateList)
                _filteredStates.postValue(stateList)
                _selectedState.postValue(stateList[0])
                Log.e(TAG, "" + stateList)
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching states: $e")
            }
        }
    }
    // Consider adding a function to update _selectedState
    fun selectState(state: State) {
        Log.d(TAG, "selectState called with state: $state")
        _selectedState.postValue(state)
    }

    fun filterStateList(filterStateName: String) {
        if (filterStateName.isEmpty()){
            Log.d(TAG, "filterStateList(): ${states.value}")
           if (states.value!=null && states.value!!.isNotEmpty()) {
               _filteredStates.postValue(states.value)
               _selectedState.postValue(states.value!![0])
           }
        }else {
            states.value?.let { it ->
                val filteredList = it.filter {
                    it.state.lowercase().contains(filterStateName.lowercase())
                }
                if (filteredList.isNotEmpty()) {
                    _selectedState.postValue(filteredList[0])
                }else{
                    _selectedState.postValue(null)
                }
                _filteredStates.postValue(filteredList)
            }
        }

    }


}

