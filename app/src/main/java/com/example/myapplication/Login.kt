package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {

    lateinit var button2 : Button
    lateinit var button3 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        button2.setOnClickListener{
            val intent = Intent(this, IdpwSearch::class.java)
            startActivity(intent) }

        button3.setOnClickListener{
            val intent = Intent(this, Join::class.java)
            startActivity(intent) }




    }
}