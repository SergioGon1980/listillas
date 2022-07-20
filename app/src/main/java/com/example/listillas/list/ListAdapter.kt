package com.example.listillas.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listillas.databinding.ItemListBinding
import com.example.listillas.list.item.Item

class ListAdapter(
    var list: List<Item>,
    var moveHandler: ((item: Item, por: Int) -> Unit),
    var editHandler: ((item: Item, por: Int) -> Unit)
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
                binding.textDate.text = this.getCreationDate()
                binding.updateButton.setOnClickListener {editHandler(this,position)}
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
