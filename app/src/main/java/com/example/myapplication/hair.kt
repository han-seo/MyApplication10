package com.example.myapplication

import ListViewItem
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_hair.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.view.*

class hair : AppCompatActivity() {
    lateinit var btn_Back2 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hair)
        val items = mutableListOf<ListViewItem>()

        btn_Back2 = findViewById(R.id.btn_Back2)
        btn_Back2.setOnClickListener{
            var intent = Intent(this,category::class.java)
            startActivity(intent)
        }

        //정보보이기기
       items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.photo1)!!, "1번", "1번 입니다"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.photo2)!!, "2번", "2번 입니다"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.photo3)!!, "3번", "3번 입니다"))

        val adapter = ListViewAdapter(items)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = parent.getItemAtPosition(position) as ListViewItem
            Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
            }
        }

}