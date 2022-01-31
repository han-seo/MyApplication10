package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button

class category : AppCompatActivity() {


    lateinit var Button1 : Button
    lateinit var Button2 : Button
    lateinit var Button3 : Button
    lateinit var Button4 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)


        Button1 =  findViewById<Button>(R.id.btn_studio)
        Button2 =  findViewById<Button>(R.id.btn_hair)
        Button3 =  findViewById<Button>(R.id.btn_model)
        Button4 =  findViewById<Button>(R.id.btn_photo)



        Button1.setOnClickListener{
            var intent=Intent(this, studio::class.java)
            startActivity(intent)
        }

        Button2.setOnClickListener{
            var intent=Intent(this, hair::class.java)
            startActivity(intent)
        }
/*
        Button3.setOnClickListener{
            var intent=Intent(this, model::class.java)
            startActivity(intent)
        }*/

        Button4.setOnClickListener{
            var intent=Intent(this, photo::class.java)
            startActivity(intent)
        }
    }

}