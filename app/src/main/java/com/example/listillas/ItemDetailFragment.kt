package com.example.listillas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class ItemDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_detail, container, false)

        val cancelButton = view.findViewById<MaterialButton>(R.id.cancelItembutton)
        val deleteButton = view.findViewById<MaterialButton>(R.id.deleteItemButtom)
        val saveButton = view.findViewById<MaterialButton>(R.id.saveItembutton)

        cancelButton.setOnClickListener { cancelButtonHandler(view) }
        deleteButton.setOnClickListener { deleteButtonHandler(view) }
        saveButton.setOnClickListener { saveButtonHandler(view) }

        return view
    }

    private fun cancelButtonHandler(view: View) {
        val title = view.findViewById<TextInputEditText>(R.id.titleField)
        val description = view.findViewById<TextInputEditText>(R.id.descriptionField)

        title.setText("")
        description.setText("")
    }

    private fun deleteButtonHandler(view: View) {
        Log.d("debug", "Click on delete Button")
    }

    private fun saveButtonHandler(view: View) {
        Log.d("debug", "Click on Save Button")
    }
}