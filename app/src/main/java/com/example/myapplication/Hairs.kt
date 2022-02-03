package com.example.myapplication

import android.animation.ValueAnimator
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
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.hairs.*
import java.util.*
import kotlin.collections.ArrayList
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.airbnb.lottie.LottieAnimationView
import com.example.myapplication.databinding.ActivityAuthBinding
import com.example.myapplication.databinding.ListItem2Binding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.list_item2.*

class Hairs : AppCompatActivity() {

    lateinit var btn : Button
    val list = ArrayList<YoutubeItem>()
    val displayList = ArrayList<YoutubeItem>()
    lateinit var like_btn : LottieAnimationView
    ///드로워 바 틀리면 지우기
    lateinit var toggle : ActionBarDrawerToggle
    ///




    //카테고리 헤어/메이크업 관련 코드입니다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hairs)

        //상단바 이름 바꾸기
        getSupportActionBar()?.setTitle("카테고리 헤어");
        //

        loadCard()

        btn = findViewById(R.id.btn)

        btn.setOnClickListener{
            val intent = Intent(this, EditList::class.java)
            startActivity(intent)
        }

        ///
        //좋아요 버튼 클릭 리스너
        //여기서 작동안함 어댑터로 가서 고치기

    }

    private fun loadCard(){

        val resources: Resources = this.resources
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.hair1)
        val resources2: Resources = this.resources
        val bitmap3 = BitmapFactory.decodeResource(resources2, R.drawable.hair2)
        val resources3: Resources = this.resources
        val bitmap4 = BitmapFactory.decodeResource(resources3, R.drawable.hair3)
        val resources4: Resources = this.resources
        val bitmap5 = BitmapFactory.decodeResource(resources4, R.drawable.hair4)
        val resources5: Resources = this.resources
        val bitmap6 = BitmapFactory.decodeResource(resources5, R.drawable.hair5)
        val resources6: Resources = this.resources
        val bitmap7 = BitmapFactory.decodeResource(resources6, R.drawable.hair6)
        val resources7: Resources = this.resources
        val bitmap8 = BitmapFactory.decodeResource(resources7, R.drawable.hair1)
        val resources8: Resources = this.resources
        val bitmap9 = BitmapFactory.decodeResource(resources8, R.drawable.hair2)
        val resources9: Resources = this.resources
        val bitmap10 = BitmapFactory.decodeResource(resources9, R.drawable.hair3)
        val resources10: Resources = this.resources
        val bitmap11 = BitmapFactory.decodeResource(resources10, R.drawable.hair4)

        //드로어에이블 말고 비트맵으로 바꿔봄, 갤러리에서 사진 가져오도록, 틀리면 원상복귀 필요
        list.add(YoutubeItem(bitmap2!!, "오뜨 디자이너","모델과의 coummunication을 최우선으로 합니다.","120"))
        list.add(YoutubeItem(bitmap3!!, "혜림 디자이너","도자기광 피부표현, 또렷한 eye 메이크업에 자신있습니다! ","231"))
        list.add(YoutubeItem(bitmap4!!, "현경금 디자이너","본연의 image를 살려, 예쁨을 더해주는 메이크업입니다.","4"))
        list.add(YoutubeItem(bitmap5!!, "하니 디자이너","탄머리, 녹은머리, 탈색 리빌딩시술 master입니다.","45"))
        list.add(YoutubeItem(bitmap6!!, "오성희 디자이너","트렌디한 감성추구 손상모 전문 beauty플래너 ","500"))
        list.add(YoutubeItem(bitmap7!!, "김지영 디자이너","trendy한 감성으로 고객님의 니즈를 파악합니다.","128"))
        list.add(YoutubeItem(bitmap8!!, "예원 디자이너","1:1 상담을 통해 원하시는 style 맞춤을 해드릴게요!","322"))
        list.add(YoutubeItem(bitmap9!!, "둘리 디자이너","drama 로맨스는 별책부록, 스타트업 등을 맡았습니다!","78"))
        list.add(YoutubeItem(bitmap10!!, "데이지 디자이너","한 끝 차이로 만들어내는 image making!","54"))
        list.add(YoutubeItem(bitmap11!!, "토토로 디자이너","고객의 두상에 맞게 디자인 하겠습니다.fighting!","111"))

        val titles = intent.getStringExtra("nick")
        val content = intent.getStringExtra("hello")

        if(content != null && titles !=null)
        {
            Toast.makeText(this, "추가되었습니다", Toast.LENGTH_SHORT).show()
            val byteArray = intent.getByteArrayExtra("image")
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            list.add(YoutubeItem(bitmap!!, titles, content,"0"))
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
