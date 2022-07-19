package com.example.listillas.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.activity.result.contract.ActivityResultContracts
import com.example.listillas.R
import com.example.listillas.databinding.ActivityLoginBinding
import com.example.listillas.firebase.FirebaseService
import com.example.listillas.menu.MenuHandler
import com.google.android.gms.common.api.ApiException
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityLoginBinding
    private val binding get() = _binding
    private val REQ_ONE_TAP = 1
    private val showOneTapUI = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initFirebase()

        val firebaseService = FirebaseService (this)

        var resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                try{
                    val credential = firebaseService.oneTapClient.getSignInCredentialFromIntent(result.data)
                    val idToken = credential.googleIdToken
                    Log.d("Debug", "TOKEN: " + idToken.toString())
                }catch (e: ApiException){
                    Log.d("Error", e.message.toString())
                }
            }
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

    //Firebase
    private fun initFirebase () {
        val firebaseService = FirebaseService (this)
    }

    fun logFragmentirebase() {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.loginContainer.id, LoginFormFragment())
        transaction.commit()
    }

}
