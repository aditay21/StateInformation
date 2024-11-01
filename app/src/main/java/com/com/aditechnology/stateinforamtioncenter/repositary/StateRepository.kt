package com.com.aditechnology.stateinforamtioncenter.repositary

import com.com.aditechnology.stateinforamtioncenter.model.State
import com.com.aditechnology.stateinforamtioncenter.network.StateApiImpl

class StateRepository(private val api: StateApiImpl) {

    suspend fun getStates(): List<State> {
        return api.getStates()
    }
}
