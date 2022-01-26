package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    //테스트용 버튼
    lateinit var Btn_Go : Button
    lateinit var Btn_shift : Button
    lateinit var button3 :Button

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //잘되는지 테스트-->안됨..
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

       button3 = findViewById(R.id.Btn_new)
       button3.setOnClickListener{
           val intent = Intent (this, newUpload::class.java)
           startActivity(intent)
       }
    }

}