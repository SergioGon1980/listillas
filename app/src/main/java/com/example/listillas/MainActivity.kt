package com.example.listillas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listillas.config.ConfigService
import com.example.listillas.databinding.ActivityMainBinding
import com.example.listillas.json.JsonActivity
import com.example.listillas.list.ListaActivity
import com.example.listillas.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val config = ConfigService()

        // TODO: check configuration an pass parameters to login Activity
        val isLogged = false

        if (isLogged){
            startActivity(Intent(this, ListaActivity::class.java).apply { })
        }/*else {
            // TODO: Is firebase or local?
            //val isLocal = true
            val intentLogin = Intent(this, LoginActivity::class.java)
            intentLogin.putExtra("isLocal", config.isLocal())
            startActivity (intentLogin)
        }*/

        if (config.isLocal()) {
            startActivity(Intent(this, JsonActivity::class.java))
        }else {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("isLocal", config.isLocal())
            startActivity(intent)
        }
    }

}