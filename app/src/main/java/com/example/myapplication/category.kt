package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button

class category : AppCompatActivity() {


    lateinit var Button1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)


        Button1 =  findViewById<Button>(R.id.btn_studio)

        Button1.setOnClickListener{
            var intent=Intent(this, studio::class.java)
            startActivity(intent)
        }
    }

}