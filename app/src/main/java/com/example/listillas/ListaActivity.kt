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

    var List: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        Log.d("debug", "List Activity Open")

        val newItem = findViewById<Button>(R.id.newButton)
        newItem.setOnClickListener {
            addNewItem()
        }
    }

    private fun addNewItem() {
        val randomText: String = Date().toString()
        List.add(randomText)

        val textView = TextView(this)
        textView.text = randomText

        val listLayout = findViewById<LinearLayout>(R.id.listLayout)
        listLayout.addView(textView)
    }
}