package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    //테스트용 버튼
    //lateinit var Btn_Go : Button
    //lateinit var Btn_shift : Button
    //이곳은 건들지 마세요! 공공재의 공간입니다


   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       supportActionBar?.hide()


       /*
       Btn_Go = findViewById(R.id.Btn_Go)
       Btn_Go.setOnClickListener{
           var intent = Intent(this, category::class.java)
           startActivity(intent)
       }

       Btn_shift = findViewById(R.id.Btn_shift)
       Btn_shift.setOnClickListener{
           var intent = Intent(this, Login::class.java)
           startActivity(intent)
       }*/
    }
}