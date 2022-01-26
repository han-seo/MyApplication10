package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_new_upload.*

class newUpload : AppCompatActivity() {
    lateinit var resultButton: Button
    lateinit var nameEditText: EditText


    var items = arrayOf("헤어/메이크업", "스튜디오", "사진작가")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_upload)

        nameEditText = findViewById(R.id.nameEditText)
        resultButton = findViewById(R.id.resultButton)

        resultButton.setOnClickListener {
            //saveData(nameEditText.text.toString().toInt())
            var intent = Intent(this, hair::class.java)
            intent.putExtra("name", nameEditText.text.toString())
            startActivity(intent)
        }


        //스피너선언
        val spinner = findViewById<Spinner>(R.id.spinner)
        val name_tv = findViewById<TextView>(R.id.name_tv)


        //어댑터 설정
        spinner.adapter = ArrayAdapter.createFromResource(this,R.array.itemList, android.R.layout.simple_spinner_item)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    //선택안함
                    0 -> {
                        name_tv.setText("")
                    }
                    //촛불
                    1 -> {
                        name_tv.setText("정수아")
                    }
                    //강낭콩
                    2 -> {
                        name_tv.setText("박준기")
                    }
                    //담쟁이
                    3 -> {
                        name_tv.setText("박준기")
                    }
                    //일치하는게 없는 경우
                    else -> {
                        name_tv.setText("")
                    }
                }
            }
        }
    }
}
  /*  private fun saveData(name: Int) {
        var pref = this.getPreferences(0)
        var editor = pref.edit()

        editor.putInt("KEY_NAME", nameEditText.text.toString().toInt()).apply()
    }*/


