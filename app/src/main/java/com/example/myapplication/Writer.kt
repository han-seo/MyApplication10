package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Writer : AppCompatActivity(){

    //작가 클래스의 작가 소개
    //홈페이지에서 연결된 사진과 텍스트 애니메이션 구현 코드
    lateinit var register :Button
    ///드로워 바
    lateinit var toggle : ActionBarDrawerToggle
    ///

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.writer)

        register = findViewById(R.id.gos)

        register.setOnClickListener{
            var intent = Intent(this, Students::class.java)
            startActivity(intent)
        }

        //상단바 이름 바꾸기
        getSupportActionBar()?.setTitle("작가 클래스");
        //


        //////////드로워////////


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
//////////////드로워 레이아웃////////




    }

    ////드로워 레이아웃////
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    ////드로워 레이아웃////

}