package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var Button : Button



    //이것도 테스트용 나중에 지우기기

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //잘되는지 테스트

       Button =  findViewById<Button>(R.id.button)

       Button.setOnClickListener{
           val intent= Intent(this, category::class.java)
           startActivity(intent)
       }


    }

}