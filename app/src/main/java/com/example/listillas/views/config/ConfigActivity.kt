package com.example.listillas.views.config

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.listillas.R
import com.example.listillas.config.ConfigService
import com.example.listillas.databinding.ActivityConfigBinding
import com.example.listillas.menu.MenuHandler
import com.example.listillas.storage.StorageTypes

class ConfigActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityConfigBinding
    private val binding get() = _binding

    private lateinit var configService: ConfigService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configService = ConfigService(this)
        refreshSelectedStorage()

        binding.localDataFlag.setOnClickListener { toggleSelctedStorage() }
        binding.FireBaseDataFlag.setOnClickListener { toggleSelctedStorage() }
    }

    // Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuHandler = MenuHandler(this,"Config")
        menuHandler.itemHandler(item)
        if (menuHandler.intent != null) {
            startActivity(menuHandler.intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toggleSelctedStorage() {
        val selectedStorage = configService.getSlectedStorage()

        if (selectedStorage == StorageTypes.FIREBASE) {
            configService.setSelectedStorage(StorageTypes.LOCAL)

        } else {
            configService.setSelectedStorage(StorageTypes.FIREBASE)
        }

        refreshSelectedStorage()

    }

    private fun refreshSelectedStorage () {
        val setectedStorage = configService.getSlectedStorage()

        binding.localDataFlag.isChecked = setectedStorage == StorageTypes.LOCAL
        binding.FireBaseDataFlag.isChecked = setectedStorage == StorageTypes.FIREBASE
    }
}