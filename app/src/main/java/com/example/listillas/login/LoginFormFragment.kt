package com.example.listillas.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.listillas.R
import com.example.listillas.databinding.FragmentLoginFormBinding
import com.example.listillas.list.ListaActivity
import com.google.android.material.button.MaterialButton

class LoginFormFragment : Fragment() {
    private var _binding: FragmentLoginFormBinding? = null
    private val bilding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginFormBinding.inflate(inflater, container,false)

        binding.nextButton.setOnClickListener {
            val intentLista = Intent (context, ListaActivity::class.java)
            startActivity(intentLista)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}