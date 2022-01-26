package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //테스트용 버튼
    lateinit var Btn_Go : Button
    lateinit var Btn_shift : Button
    lateinit var Btn_new : Button

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //잘되는지 테스트
       Btn_Go = findViewById(R.id.Btn_Go)
       Btn_Go.setOnClickListener{
           var intent = Intent(this, category::class.java)
           startActivity(intent)
       }

       Btn_shift = findViewById(R.id.Btn_shift)
       Btn_shift.setOnClickListener{
           var intent = Intent(this, Login::class.java)
           startActivity(intent)
       }

       Btn_new = findViewById(R.id.Btn_new)
       Btn_new.setOnClickListener{
           var intent = Intent(this, newPerson::class.java)
           startActivity(intent)
       }
    }
}