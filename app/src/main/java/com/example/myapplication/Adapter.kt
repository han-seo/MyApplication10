package com.example.myapplication

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter: RecyclerView.Adapter<ItemViewHolder>() {

    //작가님 예약하기 기능과 관련된 코드
    ///바꾼부분이므로 틀리면 삭제///
    private var list = mutableListOf<String>()

    ///바꾼부분이므로 틀리면 삭제///
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_item,parent,false))

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        ///바꾼부분이므로 틀리면 삭제///
        holder.s = list[position]

        holder.onDeleteClick = {
            removeItem(it)
        }

        holder.updateView()

    }

    ///바꾼부분이므로 틀리면 삭제///
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