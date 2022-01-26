package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_new_upload.*
import java.lang.Exception

class newUpload : AppCompatActivity() {
    lateinit var resultButton: Button
    lateinit var nameEditText: EditText


    //추가
    private val open_Gallery = 1
    //val Gallery2 = 0

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

        //btn_Upload.setOnClickListener{ loadImage()}
        btn_Upload.setOnClickListener { open_Gallery() }

        val items = resources.getStringArray(R.array.my_array)
        val items2 = resources.getStringArray(R.array.sns_array)

        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        val myAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items2)


        //카테고리
        spinner.adapter = myAdapter
        spinner.prompt = "카테고리를 선택해주세요."
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

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

        //sns연동
        spinner2.adapter = myAdapter2
        spinner2.prompt = "sns를 선택해주세요."

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

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

    private fun open_Gallery(){
        val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent,open_Gallery)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode==Activity.RESULT_OK) {
            var currentImageUri: Uri? = data?.data
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver,currentImageUri)
                use_Image.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        else{
            Log.d("ActivityResulit","메롱")

        }
    }
}

