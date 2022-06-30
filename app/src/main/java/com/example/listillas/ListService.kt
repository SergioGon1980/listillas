package com.example.listillas

import android.content.Context
import android.util.Log
import java.io.File

class ListService (context:Context){

    val contextList = context
    val filename = "List.txt"
    val file = File(contextList.filesDir, filename)
    var list = mutableListOf<String>()

    //Obtener datos del fichro local list.txt
    fun getListFromFile():MutableList<String> {

        if (!file.exists()){
            file.createNewFile()
        }

        //useLines es un iterador que permite tratar las lineas de un fichero de forma independiente
        list = mutableListOf()
        file.useLines {
            list.add(it.toString())
        }
        Log.d("debug", "Este es el FilesDir: " + contextList.filesDir)
        return list
    }

    fun addItemToList (item: String) {
        list.add(item)
        file.printWriter().use {
            out -> list.forEach {
                out.println(it)
            }
        }

    }
}