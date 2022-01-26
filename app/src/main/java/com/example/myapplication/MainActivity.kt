package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    //테스트용 버튼
    lateinit var Btn_Go : Button
    lateinit var Btn_shift : Button

    private fun saveData(name:String, sns :String) {
    val pref = this.getPreferences(0)
      val editor=pref.edit()
      editor.putString("KEY_NAME",name)
               .putString("KEY_SNS",sns)
              .apply()
    }

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       Btn_Go = findViewById(R.id.Btn_Go)
       Btn_Go.setOnClickListener{
           var intent = Intent(this, category::class.java)
           startActivity(intent)
       }

       Btn_shift.setOnClickListener{
           val intent = Intent(this, Login::class.java)

           startActivity(intent) }
    }



}