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
import kotlinx.android.synthetic.main.models.*
import java.util.*
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.android.material.navigation.NavigationView

class Models : AppCompatActivity() {

    lateinit var btn : Button
    val list = ArrayList<YoutubeItem>()
    val displayList = ArrayList<YoutubeItem>()
    ///드로워 바 틀리면 지우기
    lateinit var toggle : ActionBarDrawerToggle
    ///

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.models)


        //상단바 이름 바꾸기
        getSupportActionBar()?.setTitle("카테고리 모델");
        //

        //카테고리 모델 관련 코드입니다.
        val resources: Resources = this.resources
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.model1)
        val resources2: Resources = this.resources
        val bitmap3 = BitmapFactory.decodeResource(resources2, R.drawable.model2)
        val resources3: Resources = this.resources
        val bitmap4 = BitmapFactory.decodeResource(resources3, R.drawable.model3)
        val resources4: Resources = this.resources
        val bitmap5 = BitmapFactory.decodeResource(resources4, R.drawable.model5)
        val resources5: Resources = this.resources
        val bitmap6 = BitmapFactory.decodeResource(resources5, R.drawable.model6)
        val resources6: Resources = this.resources
        val bitmap7 = BitmapFactory.decodeResource(resources6, R.drawable.model1)
        val resources7: Resources = this.resources
        val bitmap8 = BitmapFactory.decodeResource(resources7, R.drawable.model2)
        val resources8: Resources = this.resources
        val bitmap9 = BitmapFactory.decodeResource(resources8, R.drawable.model3)
        val resources9: Resources = this.resources
        val bitmap10 = BitmapFactory.decodeResource(resources9, R.drawable.model5)
        val resources10: Resources = this.resources
        val bitmap11 = BitmapFactory.decodeResource(resources10, R.drawable.model6)

        ////드로어에이블 말고 비트맵으로 바꿔봄, 갤러리에서 사진 가져오도록, 틀리면 원상복귀 필요
        list.add(YoutubeItem(bitmap2!!,"지윤 모델", "헤어 / 메이크업 개인 모델입니다.","212"))
        list.add(YoutubeItem(bitmap3!!,"도영 모델", "헤어 개인 모델입니다, 탈색 안합니다.","33"))
        list.add(YoutubeItem(bitmap4!!,"구예영 모델", "아나운서 헤어를 받고 싶습니다.","56"))
        list.add(YoutubeItem(bitmap5!!,"윤주희 모델", "인물 사진 /모델 프로필 개인 모델입니다","99"))
        list.add(YoutubeItem(bitmap6!!,"이정화 모델", "웨딩 / 바디 프로필 전문 개인 모델입니다.","872"))
        list.add(YoutubeItem(bitmap7!!,"한빛모델", "바디 프로필 스튜디오 개인 모델입니다.","534"))
        list.add(YoutubeItem(bitmap8!!,"강동원 모델", "크리스마스 컨셉으로 사진을 찍고 싶습니다.","23"))
        list.add(YoutubeItem(bitmap9!!,"제니 모델","호캉스 느낌으로 사진을 찍고 싶습니다.","1"))
        list.add(YoutubeItem(bitmap10!!,"한서 모델","디즈니에 놀러온 사진을 찍고 싶습니다.","56"))
        list.add(YoutubeItem(bitmap11!!,"아리에티 모델", "영화속 한장면을 재현해보고 싶어요!","69"))


        btn = findViewById(R.id.btn)

        btn.setOnClickListener{
            val intent = Intent(this, EditList::class.java)
            startActivity(intent)
        }

        val titles = intent.getStringExtra("nick")
        val content = intent.getStringExtra("hello")



        if(content != null && titles !=null)
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