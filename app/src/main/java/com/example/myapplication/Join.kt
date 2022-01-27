package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
class Join : AppCompatActivity() {

    lateinit var Button5 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        Button5 = findViewById<Button>(R.id.btn_confirm)

        Button5.setOnClickListener {
          val intent = Intent(this, Login::class.java )

            startActivity(intent)
        }

    }

    fun printMessage (view: View) {
        var edit_id: EditText = findViewById(R.id.edit_name)
        var edit_name: EditText = findViewById(R.id.edit_name)
        var edit_sns1: EditText = findViewById(R.id.edit_name)

        var edit_id_value: String? = edit_id.text.toString()
        var edit_name_value: String? = edit_name.text.toString()
        var edit_sns1_value: String? = edit_sns1.text.toString()

        var intent_for_print: Intent = Intent(this, ConfirmMyInfo::class.java)

        intent_for_print.putExtra("key1", edit_id_value)
        intent_for_print.putExtra("key1", edit_name_value)
        intent_for_print.putExtra("key1", edit_sns1_value)

        startActivity(intent_for_print)


    }


}

