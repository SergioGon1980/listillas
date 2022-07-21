package com.example.listillas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listillas.config.ConfigService
import com.example.listillas.databinding.ActivityMainBinding
import com.example.listillas.views.listmanagement.JsonActivity
import com.example.listillas.views.list.ListaActivity
import com.example.listillas.storage.StorageTypes

class MainActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val config = ConfigService(this)
        var defaultList: String? = null

        when (config.getSlectedStorage()) {
            StorageTypes.FIREBASE -> {
                // ToDo -> startActivity(Intent(this, LoginActivity::class.java))
                defaultList = config.getDefaultFirebaseNode()
            }
            else -> { //StorageTypes.LOCAL
                defaultList = config.getDefaultFileName()
            }
        }

        if (defaultList.isNullOrBlank()) {
            startActivity(Intent(this, JsonActivity::class.java))
        } else {
            startActivity(Intent(this, ListaActivity::class.java))
        }

//        val database = Firebase.database("https://sergiogon1980-listillas-default-rtdb.europe-west1.firebasedatabase.app/")
//        val reference = database.getReference("message")
//        reference.setValue("Hello World")

    }
}