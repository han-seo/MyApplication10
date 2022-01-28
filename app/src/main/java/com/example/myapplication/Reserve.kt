package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_reserve.*
import java.util.*

class Reserve : AppCompatActivity() {

    lateinit var Btn_9 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve)


        calendarView.setOnDateChangeListener { calenderView, year, month, day ->
        }
        val calendar = Calendar.getInstance()
        println(calendar[Calendar.YEAR])
        println(calendar[Calendar.MONTH])
        println(calendar[Calendar.DAY_OF_MONTH])
    }


}
