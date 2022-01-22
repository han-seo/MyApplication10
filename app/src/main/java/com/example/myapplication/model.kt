package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class model : AppCompatActivity() {
    lateinit var btn_Back4:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model)

        btn_Back4 = findViewById(R.id.btn_Back4)
        btn_Back4.setOnClickListener{
            var intent = Intent(this,category::class.java)
            startActivity(intent)
        }

    }
}