package com.example.listillas.list

import android.content.Context
import android.util.Log
import com.example.listillas.json.JsonService
import com.example.listillas.list.item.Item
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.io.IOException

class ListService constructor(val context: Context) {

    lateinit var toDolist: ToDoList

    init {
        getListFromFile()
    }



    fun getListFromFile() {
        val file = JsonService(context).getSelectedFile()
        val json = file.inputStream().bufferedReader().use { it.readText() }
        Log.d("debug", "File.Name: " + file.name)
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<List<Item>>() {}.type
        val list = gson.fromJson<List<Item>>(json, itemType)
        toDolist = ToDoList(file.nameWithoutExtension, list)
    }
}

