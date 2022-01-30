package com.example.myapplication

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.hair_one.*
import kotlinx.android.synthetic.main.list_item2.view.*
import java.io.ByteArrayOutputStream

class HairOne:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hair_one)

        var title = intent.getStringExtra("data1")
        tv_name.text = title
        if(title !=null)
        {
            Toast.makeText(this, "클릭되었습니다", Toast.LENGTH_SHORT).show()
            val byteArray = intent.getByteArrayExtra("data2")
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            img_profile.setImageBitmap(bitmap)
        }


        //이제 버튼을 클릭하면 예약하기로 넘어가야지. 그러기 위해서 작가님 이름과 사진을 putExtra해야 한다.
        /*
        hey.setOnClickListener{
            val stream = ByteArrayOutputStream()
            val bitmap2 = (img_profile.getDrawable() as BitmapDrawable).bitmap
            val scale = (1024 / bitmap2.width.toFloat())
            val image_w = (bitmap2.width * scale).toInt()
            val image_h = (bitmap2.height * scale).toInt()
            val resize = Bitmap.createScaledBitmap(bitmap2, image_w, image_h, true)
            resize.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val byteArray = stream.toByteArray()


            //전달 전달
            var intent = Intent(this, CheckReservation::class.java)
            intent.putExtra("title_hey",tv_name.text.toString())
            intent.putExtra("image_hey", byteArray)
            startActivity(intent)
        }*/


        //애니메이션으로 좋아요 표시하기
        like_btns.setOnClickListener{
            val animator = ValueAnimator.ofFloat(0f,0.5f).setDuration(1000)
            animator.addUpdateListener { animation: ValueAnimator ->
                like_btns.setProgress(
                    animation.getAnimatedValue() as Float
                )
            }
            animator.start()
        }

        //예약 하기페이지는 CheckReservation 액티비티이다. 거기서 포문을 지우고, 이hairone엑티비티에서 받아온
        //정보를 거기에 add문으로 더할것이다.
        //필요한 정보를 보낸다.
        hey.setOnClickListener{
            var intent = Intent(this, CheckReservation::class.java)
            intent.putExtra("title_hey",tv_name.text.toString())
            startActivity(intent)
        }


    }
}