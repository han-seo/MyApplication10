package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class hair : AppCompatActivity() {
    lateinit var btn_Back2 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hair)

        btn_Back2 = findViewById(R.id.btn_Back2)
        btn_Back2.setOnClickListener{
            var intent = Intent(this,category::class.java)
            startActivity(intent)
        }
    }
}