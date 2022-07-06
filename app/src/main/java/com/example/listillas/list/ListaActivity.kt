package com.example.listillas.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.actions.FloatAction
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import com.example.listillas.ItemDetailFragment
import com.example.listillas.R
import com.example.listillas.item.Item
import com.google.android.material.floatingactionbutton.FloatingActionButton

//import { Icon } from '@material-ui/core';

class ListaActivity : AppCompatActivity() {

    var list: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        Log.d("debug", "List Activity Open")


        // Nos creamos una variable de tipo ListService que nos hemos creado
        // como clase de Kotlin y le pasamos el contexto en el que etamos (this)
        val listService = ListService(this)
        list = listService.list
        val listLayout = findViewById<LinearLayout>(R.id.listLayout)
        addListToLayout(listLayout)

        // Definimos una variable para usar el newButton
        val newItem = findViewById<FloatingActionButton>(R.id.newButton)

        // Usamos la funcion para escuchar el click del boton
        newItem.setOnClickListener() {
            addElemetToFragment()
        }
    }

    private fun addListToLayout(Layout:LinearLayout) {
        list.forEach {
            var itemview = LayoutInflater.from(this).inflate(R.layout.item_layout,null,false)
            itemview.findViewById<CheckBox>(R.id.checkbox).isChecked = it.chek
            itemview.findViewById<TextView>(R.id.textTitle).text = it.title
            itemview.findViewById<TextView>(R.id.textDescription).text = it.description
            itemview.findViewById<TextView>(R.id.textDate).text = it.getFormattedDate()
            Layout.addView(itemview)
        }
    }

    private fun addElemetToFragment() {
        //Variables para la creacion de un nuevo fragmento
        val fragmentTransacction = supportFragmentManager.beginTransaction()
        val fragment = ItemDetailFragment()

        // Queremos que incluya un nuevo elemento en el detaillayout
        fragmentTransacction.replace(R.id.detailLayout, fragment)
        fragmentTransacction.addToBackStack(null)
        fragmentTransacction.commit()
    }

    private fun addItemToLayout(text: String) {
        val textView = TextView(this)
        textView.text = text

        val listLayout = findViewById<LinearLayout>(R.id.listLayout)
        listLayout.addView(textView)
    }
}