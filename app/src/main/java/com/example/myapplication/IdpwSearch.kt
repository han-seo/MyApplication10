package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class IdpwSearch : AppCompatActivity() {

    lateinit var Button1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_id)

        Button1 =  findViewById<Button>(R.id.btn_what)

        Button1.setOnClickListener{
            var intent=Intent(this, Login::class.java)
            startActivity(intent)
        }

    }

}