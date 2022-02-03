package com.example.myapplication

import android.animation.ValueAnimator
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.hair_one.*
import kotlinx.android.synthetic.main.list_item2.view.*
import kotlinx.android.synthetic.main.mypage_dialog.view.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class HairOne:AppCompatActivity() {

    //각각의 인물을 클릭하면 나오는 세부사항 페이지. 모든 인물이 공통적으로 이 페이지에서 값만 바꿔서 나오도록 할 것임
    //쓸데없이 40명이 넘는 사람에 대한 페이지를 모두 만들지 말고 약간 붕어빵 틀같은 느낌적인 느낌으로 만든다.
    lateinit var back : Button
    /////
    lateinit var dbManager: DBManager2
    lateinit var sqlitedb : SQLiteDatabase

    ///드로워 바////
    lateinit var toggle : ActionBarDrawerToggle
    ///

    /////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hair_one)

        //상단바 이름 바꾸기
        getSupportActionBar()?.setTitle("작가님 상세 페이지");
        //

        var title = intent.getStringExtra("data1")
        var content = intent.getStringExtra("data3")
        var how = intent.getStringExtra("data4")
        var news = intent.getStringExtra("news")
        tv_name.text = title
        tv_phone.text = content
        how_manys.text = how
        my_news.text = news

        //
        dbManager = DBManager2(this,"personnelDB2",null,1)
        //

        if(title !=null && content !=null)
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


        var flag = true
        //애니메이션으로 좋아요 표시하기
        like_btns.setOnClickListener{

            if(flag){
                val up = Integer.parseInt(how_manys.text.toString()) +1
                how_manys.setText(""+up)
                flag = false
            }else{
                val up = Integer.parseInt(how_manys.text.toString()) -1
                how_manys.setText(""+up)
                flag = true
            }


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

        //작가님 찜하기
        back = findViewById(R.id.back)
        back.setOnClickListener{

            ////
            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO personnel2 VALUES('"+title+"','"+content+"');")
            sqlitedb.close()
            ////
            val intent = Intent(this,LoveIt::class.java)
            intent.putExtra("HisName",tv_name.text.toString())
            intent.putExtra("HisPhone",tv_phone.text.toString())
            intent.putExtra("intent_name2",tv_name.text.toString())

            ///팝업을 띄워서 지금 갈지 나중에 갈지 물어보기///

            val mDialogView = LayoutInflater.from(this).inflate(R.layout.loveit_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("찜하기 페이지로")

            val mAlertDialog = mBuilder.show()
            mDialogView.galleryButtons.setOnClickListener {

                Toast.makeText(this, "지금 갑니다", Toast.LENGTH_SHORT).show()
                ///
                //gallery app........................
                startActivity(intent)
                mAlertDialog.dismiss()

                ///
            }

            mDialogView.cameraButtons.setOnClickListener {
                Toast.makeText(this, "나중에 갑니다", Toast.LENGTH_SHORT).show()
                mAlertDialog.dismiss()
            }

            ///지금가기 누르면///
            //startActivity(intent)
            ///나중에 가기 물으면 페이지에 남아있기

        }

        //////////드로워 레이아웃////////


        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.nav_home ->
                {
                    var intent = Intent(this,Home::class.java)
                    startActivity(intent)

                }
                R.id.nav_hair ->
                {
                    var intent = Intent(this,Hairs::class.java)
                    startActivity(intent)

                }
                R.id.nav_studio ->
                {
                    var intent = Intent(this,Studios::class.java)
                    startActivity(intent)

                }
                R.id.nav_model->
                {
                    var intent = Intent(this,Models::class.java)
                    startActivity(intent)

                }
                R.id.nav_camera->
                {
                    var intent = Intent(this,Cameras::class.java)
                    startActivity(intent)

                }
                R.id.nav_login ->
                {
                    var intent = Intent(this,LoginTest::class.java)
                    startActivity(intent)

                }
                R.id.nav_mypage->
                {
                    var intent = Intent(this,MyPage::class.java)
                    startActivity(intent)

                }
                R.id.nav_loveit ->
                {
                    var intent = Intent(this,LoveIt::class.java)
                    startActivity(intent)

                }

            }

            true
        }
/////////드로워 레이아웃////////


    }

    ////드로워 레이아웃////
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    ////드로워 레이아웃/////

}