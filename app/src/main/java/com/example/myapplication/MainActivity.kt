package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    //버튼 클릭 이벤트 리스너 구현
    lateinit var but : Button

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        but = findViewById(R.id.but)
       but.setOnClickListener{
           var intent = Intent(this,category::class.java)
           startActivity(intent)
       }
    }
}