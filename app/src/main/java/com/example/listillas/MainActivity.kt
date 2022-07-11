package com.example.listillas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import com.example.listillas.databinding.ActivityMainBinding
import com.example.listillas.list.ListaActivity
import com.example.listillas.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: check configuration an pass parameters to login Activity
        val isLogged = false

        if (isLogged){
            startActivity(Intent(this, ListaActivity::class.java).apply { })
        }else {
            // TODO: Is firebase or local?
            //val isLocal = true
            val intentLogin = Intent(this, LoginActivity::class.java)
            intentLogin.putExtra("isLocal", false)
            startActivity (intentLogin)
        }
    }

}