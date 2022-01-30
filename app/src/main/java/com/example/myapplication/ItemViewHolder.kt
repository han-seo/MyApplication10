package com.example.myapplication

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

class ItemViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

    private val view = WeakReference(itemView)
    private lateinit var textView: TextView
    private lateinit var textViewDelete: TextView

    var s = "헤이 작가님"

    var onDeleteClick:((RecyclerView.ViewHolder)-> Unit)? = null

    init {
        view.get()?.let {

            it.setOnClickListener{
                if(view.get()?.scrollX != 0){
                    view.get()?.scrollTo(0,0)
                }
            }
            textView = it.findViewById(R.id.textView)
            textViewDelete = it.findViewById(R.id.textViewDelete)

            textViewDelete.setOnClickListener{
                onDeleteClick?.let {
                    onDeleteClick->
                    onDeleteClick(this)
                }
            }

        }
    }

    fun updateView() {
        view.get()?.scrollTo(0,0)
        textView.text = "index $s"
    }
}
