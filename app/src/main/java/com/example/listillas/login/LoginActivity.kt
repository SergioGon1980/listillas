package com.example.listillas.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import com.example.listillas.R
import com.example.listillas.databinding.ActivityLoginBinding
import com.example.listillas.menu.MenuHandler

class LoginActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityLoginBinding
    private val binding get() = _binding

    private var loginContainer: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isLocal = intent.getBooleanExtra("isLocal", true)

        if (isLocal) {
            initLocalJSON()
        }else {
            initFirebase()
        }

    }

    // Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var menuHandler = MenuHandler(this,"Login")
        menuHandler.itemHandler(item)
        if (menuHandler.intent != null) {
            startActivity(menuHandler.intent)
        }
        return super.onOptionsItemSelected(item)
    }

    fun initFirebase() {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.loginContainer.id, LoginFormFragment())
        transaction.commit()
    }

    fun initLocalJSON() {

    }

}
