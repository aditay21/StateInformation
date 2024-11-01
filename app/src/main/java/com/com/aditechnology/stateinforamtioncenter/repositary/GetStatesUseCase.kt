package com.com.aditechnology.stateinforamtioncenter.repositary

import com.com.aditechnology.stateinforamtioncenter.model.State

class GetStatesUseCase(private val repository: StateRepository) {
    suspend operator fun invoke(): List<State> {
        return repository.getStates()
    }
}
