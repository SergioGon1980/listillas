package com.example.listillas.list

import android.content.Context
import android.util.Log
import com.example.listillas.json.JsonService
import com.example.listillas.list.item.Item
import com.google.gson.GsonBuilder
import org.json.JSONArray
import java.io.IOException

class ListService constructor(val context: Context) {

    lateinit var list: ToDoList

    init {
        getListFromFile()
    }



    fun getListFromFile() {
        val file = JsonService(context).getSelectedFile()
        val json = file.inputStream().bufferedReader().use { it.readText() }

        val gson = GsonBuilder().create()
        list = gson.fromJson(json, ToDoList::class.java)
    }
}

