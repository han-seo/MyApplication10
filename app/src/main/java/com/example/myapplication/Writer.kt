package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Writer : AppCompatActivity(){

    lateinit var register :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.writer)

        register = findViewById(R.id.gos)

        register.setOnClickListener{
            var intent = Intent(this, Students::class.java)
            startActivity(intent)
        }




    }

}