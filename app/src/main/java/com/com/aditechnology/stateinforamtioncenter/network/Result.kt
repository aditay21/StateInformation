package com.com.aditechnology.stateinforamtioncenter.network

import com.com.aditechnology.stateinforamtioncenter.model.State

sealed class Result<out T> {
    data class Success(val data: State) : Result<State>()
    data class Failure(val exception: Exception) : Result<Nothing>()
}
