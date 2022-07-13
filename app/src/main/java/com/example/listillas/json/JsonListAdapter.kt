package com.example.listillas.json

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listillas.databinding.ItemJsonListBinding

class JsonListAdapter (
    var jsonList: List<JsonItem>,
    var clickHandler: ((jsonItem:JsonItem) -> Unit)

): RecyclerView.Adapter<JsonListAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemJsonListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemJsonListBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(jsonList[position]) {
                binding.root.setOnClickListener { clickHandler(this)}
                binding.jsonItemName.text = this.name
                binding.jsonItemSelected.isChecked = this.selected
            }
        }
    }

    override fun getItemCount(): Int {
        return jsonList.size
    }
}