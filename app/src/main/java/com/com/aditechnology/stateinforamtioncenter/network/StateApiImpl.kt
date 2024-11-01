package com.com.aditechnology.stateinforamtioncenter.network

import com.com.aditechnology.stateinforamtioncenter.model.State


class StateApiImpl :StateApi {

    override suspend fun getStates(): List<State> {
        return MockApi.networkRequest()

    }

    override suspend fun getStateDetails(state: String): State {
        TODO("Not yet implemented")
    }
}