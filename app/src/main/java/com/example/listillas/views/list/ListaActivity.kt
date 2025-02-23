package com.example.listillas.views.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listillas.ItemDetailFragment
import com.example.listillas.R
import com.example.listillas.databinding.ActivityListaBinding
import com.example.listillas.entities.Note
import com.example.listillas.storage.ListService
import com.example.listillas.menu.MenuHandler


class ListaActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityListaBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val list = ListService(this).toDolist.list
        val list = ListService(this).notes
        val listAdapter = ListAdapter(
            list,
            { note, pos -> moveHandler(note, pos) },
            { note, pos -> editHandler(note, pos) }
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

    private fun editHandler(note: Note, pos: Int) {

    }

    private fun moveHandler(note: Note, pos: Int) {

    }
}

