package com.com.aditechnology.stateinforamtioncenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.com.aditechnology.stateinforamtioncenter.repositary.StateRepository

class StateViewModelFactory (private val repository: StateRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedStateViewModel(repository) as T
    }
}
