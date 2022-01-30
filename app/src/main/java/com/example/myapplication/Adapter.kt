package com.example.myapplication

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter: RecyclerView.Adapter<ItemViewHolder>() {

    ///내가 바꾼부분///
    private var list = mutableListOf<String>()

    ///내가 바꾼부분///
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_item,parent,false))

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        ///내가 바꾼부분///
        holder.s = list[position]

        holder.onDeleteClick = {
            removeItem(it)
        }

        holder.updateView()

    }

    ///내가 바꾼부분///
    fun reload(list: List<String>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun removeItem(viewholder : RecyclerView.ViewHolder){

        val position = viewholder.adapterPosition

        list.removeAt(position)
        notifyItemRemoved(position)
    }


}