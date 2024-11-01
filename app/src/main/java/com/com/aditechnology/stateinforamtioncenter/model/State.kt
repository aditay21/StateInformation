package com.com.aditechnology.stateinforamtioncenter.model

data class State(
    val state: String,
    val population: Int,
    val counties: Int,
    val detail: String,
    var townName: MutableList<TownName>
)

