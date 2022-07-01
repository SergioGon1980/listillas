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

    // Variable en la que vamos a guardar todos los elementos de la lista,
    // tanto en la carga para recuperar los datos del fichero, como cuando
    // añadamos elementos desde el boton NewButton que se guardaran aqui.
    // inicializamos la lista a vacia
    var list: MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        Log.d("debug", "List Activity Open")

        // Nos creamos una variable de tipo ListService que nos hemos creado
        // como clase de Kotlin y le pasamos el contexto en el que etamos (this)
        val listService = ListService(this)
        // Invocamos a la funcion getListFromFile de la variable listService
        // (tipo Listservice) para recuperar los datos del fichero y guardarlos
        // en formato de lista
        list = listService.getListFromFile()
        //Recorremos todos los elementos de la lista
        list.forEach {
            // Añade el elemento de la iteracion actual de la lista (it)
            // al Layout llamando a la función addItemtoLayout
            addItemToLayout(it)
        }

        // Definimos una variable para usar el newButton
/*        val newItem = findViewById<Button>(R.id.newButton)
        // Usamos la funcion para escuchar el click del boton
        newItem.setOnClickListener {
            Log.d("debug)", "Click New Item")
            // invocamos la función addnewItem para escribir un nuevo elemento en
            // el fichero y añadirlo al layout. Le pasamos una variable de tipo
            // ListService para poder utilizar la funcion addItemToList
            addNewItem(listService)
        }*/
    }

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
    }
}