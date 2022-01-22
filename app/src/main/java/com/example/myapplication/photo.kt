package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class photo : AppCompatActivity() {

    lateinit var btn_Back3:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        btn_Back3 = findViewById(R.id.btn_Back3)
        btn_Back3.setOnClickListener{
            var intent = Intent(this,category::class.java)
            startActivity(intent)
        }
    }
}