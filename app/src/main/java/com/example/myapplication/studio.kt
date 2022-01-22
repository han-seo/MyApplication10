package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class studio : AppCompatActivity() {
    lateinit var btn_Back : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studio)

        btn_Back = findViewById(R.id.btn_Back)
        btn_Back.setOnClickListener{
            var intent = Intent(this,category::class.java)
            startActivity(intent)
        }
    }

}