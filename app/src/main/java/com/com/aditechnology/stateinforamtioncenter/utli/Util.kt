package com.com.aditechnology.stateinforamtioncenter.utli

import android.util.Log
import com.com.aditechnology.stateinforamtioncenter.MyApplication
import com.com.aditechnology.stateinforamtioncenter.model.State
import com.com.aditechnology.stateinforamtioncenter.model.StatesResponse
import com.com.aditechnology.stateinforamtioncenter.model.TownName
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

import java.lang.reflect.Type

import java.io.IOException

class Util {

    val TAG = Util::class.simpleName


    private fun loadJsonFromAssets(fileName: String): String? {
        return try {
            MyApplication.instance
                .assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
              Log.e(TAG,e.message.toString())
            return  null
        }
    }

    // Parse the JSON data
    private fun parseJson(json: String?): List<State> {
        if (json.isNullOrBlank()) {
            return emptyList()
        }

        val gson = Gson()
        val type: Type = StatesResponse::class.java

        return try {
            val response: StatesResponse = gson.fromJson(json, type)
            response.data.forEach {
                println("State: ${it.state}, Population: ${it.population}")
                val townNameList: MutableList<TownName> = mutableListOf()
                townNameList.add(TownName("Town A"))
                townNameList.add(TownName("Town B"))
                townNameList.add(TownName("Town C"))
                townNameList.add(TownName("Town D"))
                townNameList.add(TownName("Town E"))
                townNameList.add(TownName("Town F"))
                townNameList.add(TownName("Town G"))
                townNameList.add(TownName("Town H"))
                townNameList.add(TownName("Town I"))
                townNameList.add(TownName("Town J"))
                townNameList.add(TownName("Town K"))
                townNameList.add(TownName("Town L"))
                townNameList.add(TownName("Town M"))
                townNameList.add(TownName("Town N"))
                townNameList.add(TownName("Town O"))

                it.townName = townNameList
            }

            return response.data
        } catch (e: JsonSyntaxException) {
            Log.e("JSON Parsing", "Error parsing JSON: $e")
            emptyList()
        }
    }




    fun loadStates() : List<State> {
        val json = loadJsonFromAssets( "states_data.json")
         return parseJson(json)
        // Use the parsed data

    }

}