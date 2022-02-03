package com.example.myapplication

import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.cameras.*
import java.util.*
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.android.material.navigation.NavigationView

class Cameras : AppCompatActivity() {

    //카테고리 사진작가 목록 보여주는 관련 코드입니다
    lateinit var btn : Button
    val list = ArrayList<YoutubeItem>()
    val displayList = ArrayList<YoutubeItem>()
    ///드로워 바 틀리면 지우기
    lateinit var toggle : ActionBarDrawerToggle
    ///

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cameras)

        //상단바 이름 바꾸기
        getSupportActionBar()?.setTitle("카테고리 카메라");
        //


        //드로어에이블 말고 비트맵으로 바꿔봄, 갤러리에서 사진 가져오도록, 틀리면 원상복귀 필요

        val resources: Resources = this.resources
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.camera1)
        val resources2: Resources = this.resources
        val bitmap3 = BitmapFactory.decodeResource(resources2, R.drawable.camera2)
        val resources3: Resources = this.resources
        val bitmap4 = BitmapFactory.decodeResource(resources3, R.drawable.camera3)
        val resources4: Resources = this.resources
        val bitmap5 = BitmapFactory.decodeResource(resources4, R.drawable.camera4)
        val resources5: Resources = this.resources
        val bitmap6 = BitmapFactory.decodeResource(resources5, R.drawable.camera5)
        val resources6: Resources = this.resources
        val bitmap7 = BitmapFactory.decodeResource(resources6, R.drawable.camera6)
        val resources7: Resources = this.resources
        val bitmap8 = BitmapFactory.decodeResource(resources7, R.drawable.camera1)
        val resources8: Resources = this.resources
        val bitmap9 = BitmapFactory.decodeResource(resources8, R.drawable.camera2)
        val resources9: Resources = this.resources
        val bitmap10 = BitmapFactory.decodeResource(resources9, R.drawable.camera3)
        val resources10: Resources = this.resources
        val bitmap11 = BitmapFactory.decodeResource(resources10, R.drawable.camera4)


        list.add(YoutubeItem(bitmap2!!,"권수혁 사진작가","Photographer who captures memories.","55"))
        list.add(YoutubeItem(bitmap3!!,"한수아 사진작가", "Photographer who brings out the mood.","99"))
        list.add(YoutubeItem(bitmap4!!,"김상엽 사진작가", "A photographer who captures individuality.","121"))
        list.add(YoutubeItem(bitmap5!!,"윤아엽 사진작가", "A photographer who shows his natural side.","455"))
        list.add(YoutubeItem(bitmap6!!,"이봄 사진작가", "A photographer who captures warmth.","788"))
        list.add(YoutubeItem(bitmap7!!,"오민재 사진작가", "The photographer who takes the best photos of my life.","90"))
        list.add(YoutubeItem(bitmap8!!,"강동원 사진작가", "A writer who takes extreme sports photos well.","111"))
        list.add(YoutubeItem(bitmap9!!,"한효주 사진작가", "A writer who takes good dessert pictures.","579"))
        list.add(YoutubeItem(bitmap10!!,"이광수 사진작가", "A writer who takes good family photos.","57"))
        list.add(YoutubeItem(bitmap11!!,"토르 사진작가", "A writer who takes good pictures with friends.","98"))



        btn = findViewById(R.id.btn)

        btn.setOnClickListener{
            val intent = Intent(this, EditList::class.java)
            startActivity(intent)
        }

        val titles = intent.getStringExtra("nick")
        val content = intent.getStringExtra("hello")


        if(content != null &&titles !=null)
        {
            Toast.makeText(this, "추가되었습니다", Toast.LENGTH_SHORT).show()
            val byteArray = intent.getByteArrayExtra("image")
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

            list.add(YoutubeItem(bitmap!!, titles,content,"0"))
        }

        displayList.addAll(list)
        val adapter = RecyclerAdapter(displayList)
        //val adapter = RecyclerAdapter(list)
        ////

        ////
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        //////////드로워 레이아웃 내용 틀리면 지우기////////


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
//////////////드로워 레이아웃 내용 틀리면 지우기////////


    }

    //내가 원하는 주제 가져오도록 서치 기능 더해봄, 틀리면 지워야됨
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu!!.findItem(R.id.search)

        if(menuItem != null) {
            val searchView = menuItem.actionView as SearchView
            val editText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
            editText.hint = "Search..."
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){
                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        list.forEach{
                            if(it.title.toLowerCase(Locale.getDefault()).contains(search)){
                                displayList.add(it)
                            }
                        }
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    else{
                        displayList.clear()
                        displayList.addAll(list)
                        recyclerView.adapter!!.notifyDataSetChanged()

                    }

                    return true
                }

            })

        }

        return super.onCreateOptionsMenu(menu)
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }*/

    ////드로워 레이아웃 틀리면 지우기////
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    ////드로워 레이아웃 클리면 지우기////
}