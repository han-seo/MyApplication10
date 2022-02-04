package com.example.myapplication

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.myapplication.model.UserData
import com.example.myapplication.view.UserAdapter
import com.google.android.material.navigation.NavigationView

class LoveIt2 : AppCompatActivity() {
    //찜하기 관련 코드
    private lateinit var addsBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var userList:ArrayList<UserData>
    private lateinit var userAdapter:UserAdapter
    //
    lateinit var go : Button
    //
    /////
    //lateinit var dbManager: DBManager2
    //lateinit var sqlitedb : SQLiteDatabase
    lateinit var str_name : String
    lateinit var str_tel : String
    lateinit var layout : RecyclerView
    /////

    ///드로워 바//
    lateinit var toggle : ActionBarDrawerToggle
    ///
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.love_it)

        //상단바 이름 바꾸기
        getSupportActionBar()?.setTitle("스크랩하기(찜하기)");
        //


        ////////드로워 레이아웃////////


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
                    var intent = Intent(this,LoveIt2::class.java)
                    startActivity(intent)

                }

            }

            true
        }
////////드로워 레이아웃////////

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
    /**ok now run this */



