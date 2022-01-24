package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {

    lateinit var Button1 : Button
    lateinit var Button2 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Button1 =  findViewById<Button>(R.id.btn_search)
        Button2 =  findViewById<Button>(R.id.btn_join)

        Button1.setOnClickListener{
            val intent = Intent(this, IdpwSearch::class.java)
            startActivity(intent) }

        Button2.setOnClickListener{
            val intent = Intent(this, Join::class.java)
            startActivity(intent) }




    }
}