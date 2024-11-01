package com.com.aditechnology.stateinforamtioncenter.network


import com.com.aditechnology.stateinforamtioncenter.model.State
import com.com.aditechnology.stateinforamtioncenter.utli.Util


object MockApi {

    fun networkRequest(): List<State>{
        // Return mock data
        return Util().loadStates() ?: emptyList()
    }
}
