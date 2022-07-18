package com.example.listillas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.listillas.config.ConfigService
import com.example.listillas.databinding.ActivityMainBinding
import com.example.listillas.json.JsonActivity
import com.example.listillas.list.ListaActivity
import com.example.listillas.login.LoginActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val config = ConfigService(this)

        if (!config.getFirebaseActive()) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            val defaultFile = config.getDefaultFileName()

            if (defaultFile.isNullOrBlank()) {
                startActivity(Intent(this, JsonActivity::class.java))
            } else {
                val intent = Intent(this, ListaActivity::class.java)
                intent.putExtra("fileName", defaultFile)
                startActivity(intent)
            }
        }

        val database = Firebase.database("https://sergiogon1980-listillas-default-rtdb.europe-west1.firebasedatabase.app/")
        val reference = database.getReference("message")
        reference.setValue("Hello World")

    }
}