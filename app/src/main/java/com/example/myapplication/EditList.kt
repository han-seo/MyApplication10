package com.example.myapplication


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.mypage_dialog.view.*
import java.io.ByteArrayOutputStream
import java.io.File

class EditList: AppCompatActivity() {

    //카테고리 항목을 새로 갱신할때 뜨는 새로운 페이지
    lateinit var save : Button //Mypage의 디렉토리에 전달할 소중한 값들....
    lateinit var nick: EditText
    lateinit var hello: EditText
    lateinit var thatImage : ImageView //이미지는 추후에 만들자 <- 만들었다

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_list)

        save = findViewById(R.id.save)
        nick = findViewById(R.id.nick)
        hello = findViewById(R.id.hello)
        thatImage = findViewById(R.id.thatImage)

        //상단바 이름 바꾸기
        getSupportActionBar()?.setTitle("작가 업로드");
        //

        //이미지는따로 사진을 클릭하면 갤러리에 들어가서 고를 수 있도록 한다.
        thatImage.setOnClickListener{
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            intent.type = "image/*"
            startActivityForResult(intent, 10)
        }

        save.setOnClickListener{
            /*굳이 안해도 될듯
            saveData(title.text.toString(),
                content.text.toString(),
            thatDay.text.toString(),
            )*/
            val stream = ByteArrayOutputStream()
            val bitmap = (thatImage.getDrawable() as BitmapDrawable).bitmap
            val scale = (1024 / bitmap.width.toFloat())
            val image_w = (bitmap.width * scale).toInt()
            val image_h = (bitmap.height * scale).toInt()
            val resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true)
            resize.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val byteArray = stream.toByteArray()


            //전달 전달
            var intent = Intent(this, Hairs::class.java)
            intent.putExtra("nick",nick.text.toString())
            intent.putExtra("hello",hello.text.toString())
            intent.putExtra("image", byteArray)
            startActivity(intent)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 10 && resultCode === Activity.RESULT_OK) {

            //이미지 로딩 이미지 보내자
            var inputStream = contentResolver.openInputStream(data!!.data!!)
            val bitmap = BitmapFactory.decodeStream(inputStream, null,null)
            inputStream!!.close()
            inputStream = null
            bitmap?.let {
                thatImage.setImageBitmap(bitmap)
            } ?: let {
                Log.d("kkang", "bitmap null.............")
            }
        }
    }


    /*
    private fun saveData(titles: String, contents:String, thatDays : String) {
        var pref = this.getPreferences(0)
        var editor = pref.edit() //데이터 입력

        editor.putString("KEY_TITLE",title.text.toString()).apply()
        editor.putString("KEY_CONTENT",content.text.toString()).apply()
        editor.putString("KEY_THATDAY", thatDay.text.toString()).apply()

    }

    private fun loadData(){
        var pref = this.getPreferences(0) //가져오는 기능 호출
        var titles = pref.getString("KEY_TITLE", "")
        var contents = pref.getString("KEY_CONTENT","") //해당 키값 호출, 없으면 0 설정
        var thatDays = pref.getString("KEY_THATDAY","")

        if(titles != "" && contents != "" && thatDays !=""){
            title.setText(titles.toString())
            content.setText(contents.toString())
            thatDay.setText(thatDays.toString())
        }
    }*/
}