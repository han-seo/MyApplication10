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
import kotlinx.android.synthetic.main.studios.*
import java.util.*
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.android.material.navigation.NavigationView

class Studios : AppCompatActivity() {

    //카테고리 스튜디오 관련 코드입니다.
    lateinit var btn : Button
    val list = ArrayList<YoutubeItem>()
    val displayList = ArrayList<YoutubeItem>()
    ///드로워 바 틀리면 지우기
    lateinit var toggle : ActionBarDrawerToggle
    ///

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.studios)

        //상단바 이름 바꾸기
        getSupportActionBar()?.setTitle("카테고리 스튜디오");
        //

        val resources: Resources = this.resources
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.s1)
        val resources2: Resources = this.resources
        val bitmap3 = BitmapFactory.decodeResource(resources2, R.drawable.s2)
        val resources3: Resources = this.resources
        val bitmap4 = BitmapFactory.decodeResource(resources3, R.drawable.s3)
        val resources4: Resources = this.resources
        val bitmap5 = BitmapFactory.decodeResource(resources4, R.drawable.s4)
        val resources5: Resources = this.resources
        val bitmap6 = BitmapFactory.decodeResource(resources5, R.drawable.s5)
        val resources6: Resources = this.resources
        val bitmap7 = BitmapFactory.decodeResource(resources6, R.drawable.s6)
        val resources7: Resources = this.resources
        val bitmap8 = BitmapFactory.decodeResource(resources7, R.drawable.s1)
        val resources8: Resources = this.resources
        val bitmap9 = BitmapFactory.decodeResource(resources8, R.drawable.s2)
        val resources9: Resources = this.resources
        val bitmap10 = BitmapFactory.decodeResource(resources9, R.drawable.s3)
        val resources10: Resources = this.resources
        val bitmap11 = BitmapFactory.decodeResource(resources10, R.drawable.s4)


        //드로어에이블 말고 비트맵으로 바꿔봄, 갤러리에서 사진 가져오도록, 틀리면 원상복귀 필요
        list.add(YoutubeItem(bitmap2!!, "Lina 스튜디오","화이트 컨셉으로 이루어진 스튜디오","11"))
        list.add(YoutubeItem(bitmap3!!, "Celebrity 스튜디오","자연스러운 사진 촬영을 추구하는 스튜디오","34"))
        list.add(YoutubeItem(bitmap4!!, "Half 스튜디오","베이지 우드느낌의 따뜻한 감성의 스튜디오","423"))
        list.add(YoutubeItem(bitmap5!!, "Photo 스튜디오","루프탑 촬영이 가능한 스튜디오","56"))
        list.add(YoutubeItem(bitmap6!!, "Life 스튜디오","감각적인 느낌으로 인생사진 찍는 스튜디오","91"))
        list.add(YoutubeItem(bitmap7!!, "MoodOnThe 스튜디오","유럽느낌이 물씬 나는 스튜디오","156"))
        list.add(YoutubeItem(bitmap8!!, "Totoro 스튜디오","지브리 영화를 재현한 스튜디오","87"))
        list.add(YoutubeItem(bitmap9!!, "Disney 스튜디오","디즈니 세상을 구현한 덕후 스튜디오","776"))
        list.add(YoutubeItem(bitmap10!!, "Influencer 스튜디오","연예인이 찍는 화보같은 느낌의 스튜디오","90"))
        list.add(YoutubeItem(bitmap11!!, "Pororo 스튜디오","아이들 전용 스튜디오","5"))



        btn = findViewById(R.id.btn)

        btn.setOnClickListener{
            val intent = Intent(this, EditList::class.java)
            startActivity(intent)
        }

        val titles = intent.getStringExtra("nick")
        val content = intent.getStringExtra("hello")


        if(content != null && titles!=null)
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

    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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