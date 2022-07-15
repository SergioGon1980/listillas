package com.example.listillas.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listillas.ItemDetailFragment
import com.example.listillas.R
import com.example.listillas.databinding.ActivityListaBinding
import com.example.listillas.list.item.Item
import com.example.listillas.menu.MenuHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton

//import { Icon } from '@material-ui/core';

class ListaActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityListaBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val list = ListService(this).list
        val listAdapter = ListAdapter(
            list,
            {item, pos -> moveHandler(item, pos)},
            { item, pos -> editHandler (item, pos) }
        )

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.listContainer.layoutManager = layoutManager
        binding.listContainer.adapter = listAdapter


        // Usamos la funcion para escuchar el click del boton
        binding.newButton.setOnClickListener() {
            addElemetToFragment()
        }
    }

    // Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var menuHandler = MenuHandler(this,"List")
        menuHandler.itemHandler(item)
        if (menuHandler.intent != null) {
            startActivity(menuHandler.intent)
        }
        return super.onOptionsItemSelected(item)
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

        val listLayout = findViewById<LinearLayout>(R.id.listContainer)
        listLayout.addView(textView)
    }
}

    private fun editHandler(item: Item, pos: Int) {

    }

    private fun moveHandler(item: Item, pos: Int) {

    }