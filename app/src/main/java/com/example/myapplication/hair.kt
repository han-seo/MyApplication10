package com.example.myapplication

import ListViewItem
import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_hair.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.view.*

class hair : AppCompatActivity() {
    lateinit var btn_Back2: Button
    lateinit var btn_ok2 :Button

    var imm : InputMethodManager?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hair)
        val items = mutableListOf<ListViewItem>()

        btn_Back2 = findViewById(R.id.btn_Back2)
        btn_Back2.setOnClickListener {
            var intent = Intent(this, category::class.java)
            startActivity(intent)
        }

        imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager?

        btn_ok2 = findViewById(R.id.btn_ok2)



        //정보보이기기
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.photo1)!!, "1번", "1번 입니다"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.photo2)!!, "2번", "2번 입니다"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.photo3)!!, "3번", "3번 입니다"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.photo4)!!, "4번", "4번 입니다"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.photo5)!!, "5번", "5번 입니다"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.photo5)!!, "6번", "6번 입니다"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.photo5)!!, "7번", "7번 입니다"))

        val adapter = ListViewAdapter(items)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = parent.getItemAtPosition(position) as ListViewItem
            Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        }
    }
    fun hideKeyboard(v: View){
        if(v!=null){
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

}