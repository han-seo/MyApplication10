package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_join.*

class Join : AppCompatActivity() {

    lateinit var Button5: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        Button5 = findViewById<Button>(R.id.btn_confirm)

        Button5.setOnClickListener {
            val intent = Intent(this, Login::class.java)

            startActivity(intent)
        }

        val items3 = resources.getStringArray(R.array.pick_array)
        val myAdapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items3)

        spinner3.adapter = myAdapter3
        spinner3.prompt = "카테고리를 선택해주세요."
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when (position) {
                    0 -> {

                    }
                    1 -> {

                    }
                    //...
                    else -> {

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }

//spinner.setSelection(2)

    }

    fun printMessage(view: View) {
        var edit_id: EditText = findViewById(R.id.edit_name)
        var edit_name: EditText = findViewById(R.id.edit_name)
        var edit_sns1: EditText = findViewById(R.id.edit_name)

        var edit_id_value: String? = edit_id.text.toString()
        var edit_name_value: String? = edit_name.text.toString()
        var edit_sns1_value: String? = edit_sns1.text.toString()

        var intent_for_print: Intent = Intent(this, ConfirmMyInfo::class.java)

        intent_for_print.putExtra("key1", edit_id_value)
        intent_for_print.putExtra("key2", edit_name_value)
        intent_for_print.putExtra("key3", edit_sns1_value)

        startActivity(intent_for_print)


    }

}
