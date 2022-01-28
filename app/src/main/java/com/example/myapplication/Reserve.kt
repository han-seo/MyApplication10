package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_reserve.*
import java.util.*

class Reserve : AppCompatActivity() {

    lateinit var Btn_9 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve)


        Btn_9=findViewById(R.id.Btn_9)

        calendarView.setOnDateChangeListener { calenderView, year, month, day ->

            Reserve_tv.visibility= View.VISIBLE //  선택시 보이도록
            linearLayout.visibility=View.VISIBLE
            linearLayout2.visibility=View.VISIBLE

            Btn_9.setOnClickListener{
                Btn_reserve.visibility=View.VISIBLE
            }
        }
        val calendar = Calendar.getInstance()
        println(calendar[Calendar.YEAR])
        println(calendar[Calendar.MONTH])
        println(calendar[Calendar.DAY_OF_MONTH])



    }

}

