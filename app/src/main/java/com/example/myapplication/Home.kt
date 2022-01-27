package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class Home: AppCompatActivity() {

    lateinit var hairs:Button
    lateinit var studios : Button
    lateinit var models: Button
    lateinit var cameraman : Button
    lateinit var goMap : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        hairs = findViewById(R.id.hairs)
        studios = findViewById(R.id.studios)
        models = findViewById(R.id.models)
        cameraman = findViewById(R.id.cameraman)
        goMap = findViewById(R.id.goMap)

        //각 카테고리 클릭릭
        hairs.setOnClickListener{
            val intent = Intent(this, hair::class.java)
            startActivity(intent)
        }

        studios.setOnClickListener{
            val intent = Intent(this, studio::class.java)
            startActivity(intent)
        }

        models.setOnClickListener{
            val intent = Intent(this, model::class.java)
            startActivity(intent)
        }

        cameraman.setOnClickListener{
            val intent = Intent(this, photo::class.java)
            startActivity(intent)
        }

        //구글지도
        goMap.setOnClickListener{
            val intent = Intent(this, EventMap::class.java)
            startActivity(intent)
        }

        //오늘의 추천




    }
}