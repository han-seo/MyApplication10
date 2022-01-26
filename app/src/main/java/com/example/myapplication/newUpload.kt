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

    val items = resources.getStringArray(R.array.my_array)

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

        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        spinner.adapter = myAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when (position) {
                    0 -> {

                    }
                    1 -> {

                    }
                    //...
                    else -> {

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }


    }
}


        //스피너선언
        //val spinner = findViewById<Spinner>(R.id.spinner)

  /*  private fun saveData(name: Int) {
        var pref = this.getPreferences(0)
        var editor = pref.edit()

        editor.putInt("KEY_NAME", nameEditText.text.toString().toInt()).apply()
    }*/


