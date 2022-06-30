package com.example.listillas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.ButtonBarLayout
import java.util.*

//import { Icon } from '@material-ui/core';

class ListaActivity : AppCompatActivity() {

    var list: MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        Log.d("debug", "List Activity Open")

        val listService = ListService(this)
        list = listService.getListFromFile()
        list.forEach {
            addItemToLayout(it)
        }

        val newItem = findViewById<Button>(R.id.newButton)
        newItem.setOnClickListener {
            Log.d("debug)", "Click New Item")
            addNewItem(listService)
        }
    }

    private fun addNewItem(service: ListService) {

        //Generar una variable que guarda la fecha y meterla en la lista
        val randomText: String = Date().toString()
        //list.add(randomText)
        service.addItemToList(randomText)
    }

    private fun addItemToLayout(text: String) {

        val textView = TextView(this)
        textView.text

        val lisLayout = findViewById<LinearLayout>(R.id.listLayout)
        textView.text = text
    }
}