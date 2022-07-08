package com.example.listillas.menu

import android.content.Intent
import android.util.Log
import android.view.MenuItem
import com.example.listillas.R
import com.example.listillas.config.ConfigActivity

class MenuItems {
    fun itemHandler(item: MenuItem) {
        when (item.itemId) {
            R.id.configMenu -> openConfig()
            R.id.listMenu -> openList()
            R.id.loginMenu -> openLogin()
        }
    }

    private fun openConfig() {
        Log.d("debug", "Menu: Config")
    }

    private fun openList() {
        Log.d("debug", "Menu: List")
    }

    private fun openLogin() {
        Log.d("debug", "Menu: Login")
    }
}