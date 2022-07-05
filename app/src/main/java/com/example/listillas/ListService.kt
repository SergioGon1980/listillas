package com.example.listillas

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.time.LocalDateTime
import java.util.*

class ListService (context:Context){

    val contextList = context
    val filename = "List.txt"
    val file = File(contextList.filesDir, filename)

    //Esto es para fichero
    //var list = mutableListOf<String>()

    //Esto es para el fichero de JSON
    var list = mutableListOf<Item>()


    var json = JSONArray()

    init {
        try {
            json = JSONArray(context.assets.open("example.json").bufferedReader().use { it.readText() })
            for (i in 0 until  json.length()) {
                var jsonObject = json.getJSONObject(i)
                var item: Item = Item(
                    jsonObject.optString("title"),
                    jsonObject.optString("description"),
                    jsonObject.optString("creationDate"),
                    jsonObject.getBoolean("title")
                )
                list.add(item)
            }
        }catch (ioException: IOException) {
            Log.d("Error", ioException.toString())
        }
    }

    /* *** USO PARA FICHEROS ***
    //Obtener datos del fichero local list.txt - quedara obsoleta al sutituir el fichero
    // por un JSON
    fun getListFromFile():MutableList<String> {

        if (!file.exists()){
            file.createNewFile()
        }

        list = file.readLines() as MutableList<String>
        return list
    }

    fun addItemToList (item: String) {
        list.add(item)
        file.printWriter().use {
            out -> list.forEach {
                out.println(it)
            }
        }

    }*/
}