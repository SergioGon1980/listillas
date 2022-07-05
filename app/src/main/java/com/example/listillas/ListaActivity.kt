package com.example.listillas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.ButtonBarLayout
import androidx.fragment.app.Fragment
import org.json.JSONArray
import java.util.*

//import { Icon } from '@material-ui/core';

class ListaActivity : AppCompatActivity() {

    /* *** USO SOLO PARA FICHERO ***
    // Variable en la que vamos a guardar todos los elementos de la lista,
    // tanto en la carga para recuperar los datos del fichero, como cuando
    // añadamos elementos desde el boton NewButton que se guardaran aqui.
    // inicializamos la lista a vacia
    var list: MutableList<String> = mutableListOf()*/

    // Variable en la que vamos a guardar el JSON
    //val json = JSONArray()

    var list: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        Log.d("debug", "List Activity Open")

        val listLayout = findViewById<LinearLayout>(R.id.listLayout)
        // Nos creamos una variable de tipo ListService que nos hemos creado
        // como clase de Kotlin y le pasamos el contexto en el que etamos (this)
        val listService = ListService(this)
        list = listService.list
        addListToLayout(listLayout)



        /* *** USO SOLO PARA FICHERO ***
        //Invocamos a la funcion getListFromFile de la variable listService
        // (tipo Listservice) para recuperar los datos del fichero y guardarlos
        // en formato de lista
        list = listService.getListFromFile()
        //Recorremos todos los elementos de la lista
        list.forEach {
            // Añade el elemento de la iteracion actual de la lista (it)
            // al Layout llamando a la función addItemtoLayout
            addItemToLayout(it)
        }*/

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

        //vamos a escuchar los click en los elementos de la lista del Layout
        listLayout.setOnClickListener {
        Log.d("debug", "Click en" + it)
        }
    }

    private fun addListToLayout(Layout:LinearLayout) {

    }

    /* ** USAR SOLO PARA FICHERO
    // Funcion para escribir en el fichero y añadir elemento al layout
    private fun addNewItem(service: ListService) {

        // Generar una variable que guarda la fecha y meterla en la lista
        val randomText: String = Date().toString()
        // Llamamos a la función aaItemToList de la clase ListService para
        // Escribir el nuevo elemento en el fichero
        service.addItemToList(randomText)
        // Añadir nuevo elemento al layout y le pasamos la fecha recuperada
    addItemToLayout(randomText)
    }

    // Funcion para añadir elemento al layout
    private fun addItemToLayout(text: String) {

    // Definimos una variable de tipo Textview para darle el valor del texto
    // recibido al invocar la función
    val textView = TextView(this)
    textView.text = text

    // Definimos una variable con el Layout para aññadirle un nuevo textView
    // con el texto recivido
    val listLayout = findViewById<LinearLayout>(R.id.listLayout)
    listLayout.addView(textView)
    }*/
}