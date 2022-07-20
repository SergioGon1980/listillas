package com.example.listillas.menu

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.MenuItem
import com.example.listillas.R
import com.example.listillas.views.config.ConfigActivity
import com.example.listillas.views.listmanagement.JsonActivity
import com.example.listillas.list.ListaActivity
import com.example.listillas.views.login.LoginActivity

class MenuHandler constructor(val context:Context, val current:String){

    var intent: Intent? = null

    fun itemHandler(item: MenuItem) {
        when (item.itemId) {
            R.id.configMenu -> openConfig(current)
            R.id.listMenu -> openList(current)
            R.id.loginMenu -> openLogin(current)
            R.id.JsonMenu-> openJson(current)
        }
    }

    private fun openConfig(current: String) {
        if (current == "Config") {
            intent = null
        } else {
            intent = Intent(context, ConfigActivity::class.java)
        }

        Log.d("debug", "Menu: Config")
    }

    private fun openList(current: String) {
        if (current == "List") {
            intent = null
        } else {
            intent = Intent(context,ListaActivity::class.java)
        }
        Log.d("debug", "Menu: List")
    }

    private fun openLogin(current: String) {
        if (current == "Login") {
            intent = null
        } else {
            intent = Intent(context, LoginActivity::class.java)
        }
        Log.d("debug", "Menu: Login")
    }

    private fun openJson(current: String) {
        if (current == "Json") {
            intent = null
        } else {
            intent = Intent(context, JsonActivity::class.java)
        }
        Log.d("debug", "Menu: Json")
    }
}