package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TestFile : AppCompatActivity(){

    //이부분은 김민혜 마이페이지에 들어가기 위한 버튼입니다(시험용)
    lateinit var Button : Button
    lateinit var Button2 : Button
    lateinit var Button3 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_file)

        //김민혜 마이페이지로 넘어가기 위한 부분입니다(시험용)
        Button = findViewById(R.id.button)
        Button.setOnClickListener{
            val intent = Intent(this, MyPage::class.java)
            startActivity(intent)
        }

        Button2 = findViewById(R.id.button2)
        Button2.setOnClickListener{
            val intent = Intent(this, EventMap::class.java)
            startActivity(intent)
        }
        Button3 = findViewById(R.id.button3)
        Button3.setOnClickListener{
            val intent = Intent(this, newTry::class.java)
            startActivity(intent)
        }
    }
}