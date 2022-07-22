package com.example.listillas.views.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listillas.databinding.ItemListBinding
import com.example.listillas.entities.Note
import java.text.SimpleDateFormat

class ListAdapter(
    var list: List<Note>,
    var moveHandler: ((item: Note, por: Int) -> Unit),
    var editHandler: ((item: Note, por: Int) -> Unit)
): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder (val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.root.setOnClickListener {moveHandler(this, position)}
                binding.textTitle.text = this.title
                binding.textDescription.text = this.description
                binding.checkbox.isChecked = this.chek
                binding.textDate.text = getFormattedDate(this.creationDate)
                binding.updateButton.setOnClickListener {editHandler(this,position)}
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun getFormattedDate(date: Long):String {
        val format = SimpleDateFormat("dd-MM-yyyy")
        return format.format(date)
    }
}
