package com.example.listillas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.view.menu.MenuView
import androidx.appcompat.widget.ButtonBarLayout
import androidx.fragment.app.Fragment
import org.json.JSONArray
import java.util.*

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
        val newItem = findViewById<Button>(R.id.newButton)
        // Usamos la funcion para escuchar el click del boton
        newItem.setOnClickListener {
            // Queremos que incluya un nuevo elemento en el detaillayout

            //Variables para la creacion de un nuevo fragmento
            val fragmentTransacction = supportFragmentManager.beginTransaction()
            val fragment = ItemDetailFragment()

            fragmentTransacction.replace(R.id.detailLayout, fragment)
            fragmentTransacction.addToBackStack(null)
            fragmentTransacction.commit()
        }
    }

    private fun addListToLayout(Layout:LinearLayout) {
        list.forEach {
            var itemview = LayoutInflater.from(this).inflate(R.layout.item_layout,null,false)
            itemview.findViewById<CheckBox>(R.id.checkbox).isChecked = it.chek
            itemview.findViewById<TextView>(R.id.textTitle).text = it.title
            itemview.findViewById<TextView>(R.id.textDescription).text = it.description
            itemview.findViewById<TextView>(R.id.textDate).text = it.date
            Layout.addView(itemview)
        }
    }

    private fun addItemToLayout(text: String) {
        val textView = TextView(this)
        textView.text = text

        val listLayout = findViewById<LinearLayout>(R.id.listLayout)
        listLayout.addView(textView)
    }
}