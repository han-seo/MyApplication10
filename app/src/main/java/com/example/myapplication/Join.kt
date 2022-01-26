package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
class Join : AppCompatActivity() {

    lateinit var Button5 : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        Button5 = findViewById<Button>(R.id.btn_confirm)

        Button5.setOnClickListener {
          val intent = Intent(this, Login::class.java )

            startActivity(intent)
        }


    }

}

