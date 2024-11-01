package com.com.aditechnology.stateinforamtioncenter.network

import com.com.aditechnology.stateinforamtioncenter.model.State

interface StateApi {
   // @GET("/states")
    suspend fun getStates(): List<State>

   // @GET("/states/{state}")
    suspend fun getStateDetails(state: String): State
}

