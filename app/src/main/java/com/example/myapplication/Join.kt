package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
class Join : AppCompatActivity() {

    lateinit var button5 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        button5.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }


    }

}