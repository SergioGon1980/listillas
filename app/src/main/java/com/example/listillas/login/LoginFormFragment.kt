package com.example.listillas.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.listillas.R
import com.example.listillas.list.ListaActivity
import com.google.android.material.button.MaterialButton

class LoginFormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_form, container, false)

        val buttonNext = view.findViewById<MaterialButton>(R.id.next_button)

        buttonNext.setOnClickListener {
            val intentLista = Intent (context, ListaActivity::class.java)
            startActivity(intentLista)
        }

        return view
    }

}