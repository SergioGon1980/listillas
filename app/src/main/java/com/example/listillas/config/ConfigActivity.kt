package com.example.listillas.config

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.listillas.R
import com.example.listillas.databinding.ActivityConfigBinding
import com.example.listillas.databinding.ActivityListaBinding
import com.example.listillas.menu.MenuHandler

class ConfigActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityConfigBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var menuHandler = MenuHandler(this,"Config")
        menuHandler.itemHandler(item)
        if (menuHandler.intent != null) {
            startActivity(menuHandler.intent)
        }
        return super.onOptionsItemSelected(item)
    }
}