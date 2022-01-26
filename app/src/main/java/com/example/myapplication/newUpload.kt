package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class newUpload : AppCompatActivity() {
    lateinit var  resultButton : Button
    lateinit var  nameEditText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_upload)

            nameEditText =findViewById(R.id.nameEditText)

            resultButton.setOnClickListener{
                saveData(nameEditText.text.toString().toInt())
                var intent = Intent(this, hair::class.java)
                intent.putExtra("name",nameEditText.text.toString())
                startActivity(intent)
            }

        }
    private fun saveData(name: Int){
        var pref =this.getPreferences(0)
        var editor = pref.edit()

        editor.putInt("KEY_NAME", nameEditText.text.toString().toInt()).apply()
    }
}
