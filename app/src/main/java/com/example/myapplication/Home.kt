package com.example.myapplication

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import android.util.Pair
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class Home: AppCompatActivity() {

    lateinit var hairs:Button
    lateinit var studios : Button
    lateinit var models: Button
    lateinit var cameraman : Button
    lateinit var goMap : Button
    private lateinit var viewPager2: ViewPager2
    private val sliderHandler = Handler()
    lateinit var mListLayout: RelativeLayout
    lateinit var mProfileImage : ImageView
    lateinit var mNameText : TextView
    lateinit var mDescText : TextView
    //
    lateinit var button_1 : Button
    lateinit var button_2 : Button
    lateinit var button_3 : Button
    lateinit var button_4 : Button
    //
    ///드로워 바
    lateinit var toggle : ActionBarDrawerToggle
    ///
    //프로필 사진
    lateinit var home_profile : ImageView
    //

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        //상단바 이름 바꾸기
        getSupportActionBar()?.setTitle("PicKit");
        //

        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        hairs = findViewById(R.id.hairs)
        studios = findViewById(R.id.studios)
        models = findViewById(R.id.models)
        cameraman = findViewById(R.id.cameraman)
        goMap = findViewById(R.id.goMap)

        ////////
        //이미지 받아오기


        home_profile = findViewById(R.id.home_profile)

        val byteArray = intent.getByteArrayExtra("image")
        if(byteArray!=null){
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            home_profile.setImageBitmap(bitmap)
        }
        ///////

        //각 카테고리 클릭
        hairs.setOnClickListener{
            val intent = Intent(this, Hairs::class.java)
            startActivity(intent)
        }

        studios.setOnClickListener{
            val intent = Intent(this, Studios::class.java)
            startActivity(intent)
        }

        models.setOnClickListener{
            val intent = Intent(this, Models::class.java)
            startActivity(intent)
        }

        cameraman.setOnClickListener{
            val intent = Intent(this, Cameras::class.java)
            startActivity(intent)
        }

        //구글지도
        goMap.setOnClickListener{
            val intent = Intent(this, EventMap::class.java)
            startActivity(intent)
        }

        //오늘의 추천
        viewPager2 = findViewById(R.id.viewPager_ImageSlider)

        val sliderItems: MutableList<SliderItem> = ArrayList()

        sliderItems.add(SliderItem(R.drawable.home_h1))
        sliderItems.add(SliderItem(R.drawable.home_h2))
        sliderItems.add(SliderItem(R.drawable.home_h3))
        sliderItems.add(SliderItem(R.drawable.home_h4))
        sliderItems.add(SliderItem(R.drawable.home_h5))

        //클릭되는 버튼의 종류에 따라 보여지는 사진의 종류가 달라지도록 한다.
        button_1 = findViewById(R.id.button_1)
        button_2 = findViewById(R.id.button_2)
        button_3 = findViewById(R.id.button_3)
        button_4 = findViewById(R.id.button_4)

        button_1.setOnClickListener{
            val sliderItems: MutableList<SliderItem> = ArrayList()
            sliderItems.add(SliderItem(R.drawable.home_h1))
            sliderItems.add(SliderItem(R.drawable.home_h2))
            sliderItems.add(SliderItem(R.drawable.home_h3))
            sliderItems.add(SliderItem(R.drawable.home_h4))
            sliderItems.add(SliderItem(R.drawable.home_h5))

            viewPager2.adapter = SliderAdapter(sliderItems, viewPager2)

            viewPager2.clipToPadding = false
            viewPager2.clipChildren = false
            viewPager2.offscreenPageLimit = 3
            viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(30))
            compositePageTransformer.addTransformer{ page, position ->
                val r = 1- abs(position)
                page.scaleY = 0.85f +r * 0.25f

            }

            viewPager2.setPageTransformer(compositePageTransformer)


            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandler.removeCallbacks(sliderRunnable)
                    sliderHandler.postDelayed(sliderRunnable, 2000)
                }
            })
        }


        button_2.setOnClickListener{
            val sliderItems: MutableList<SliderItem> = ArrayList()
            sliderItems.add(SliderItem(R.drawable.home_s1))
            sliderItems.add(SliderItem(R.drawable.home_s2))
            sliderItems.add(SliderItem(R.drawable.home_s3))
            sliderItems.add(SliderItem(R.drawable.home_s6))
            sliderItems.add(SliderItem(R.drawable.home_s1))

            viewPager2.adapter = SliderAdapter(sliderItems, viewPager2)

            viewPager2.clipToPadding = false
            viewPager2.clipChildren = false
            viewPager2.offscreenPageLimit = 3
            viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(30))
            compositePageTransformer.addTransformer{ page, position ->
                val r = 1- abs(position)
                page.scaleY = 0.85f +r * 0.25f

            }

            viewPager2.setPageTransformer(compositePageTransformer)


            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandler.removeCallbacks(sliderRunnable)
                    sliderHandler.postDelayed(sliderRunnable, 2000)
                }
            })
        }


        button_3.setOnClickListener{
            val sliderItems: MutableList<SliderItem> = ArrayList()
            sliderItems.add(SliderItem(R.drawable.home_m1))
            sliderItems.add(SliderItem(R.drawable.home_m6))
            sliderItems.add(SliderItem(R.drawable.home_m3))
            sliderItems.add(SliderItem(R.drawable.home_m4))
            sliderItems.add(SliderItem(R.drawable.home_m5))

            viewPager2.adapter = SliderAdapter(sliderItems, viewPager2)

            viewPager2.clipToPadding = false
            viewPager2.clipChildren = false
            viewPager2.offscreenPageLimit = 3
            viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(30))
            compositePageTransformer.addTransformer{ page, position ->
                val r = 1- abs(position)
                page.scaleY = 0.85f +r * 0.25f

            }

            viewPager2.setPageTransformer(compositePageTransformer)


            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandler.removeCallbacks(sliderRunnable)
                    sliderHandler.postDelayed(sliderRunnable, 2000)
                }
            })
        }


        button_4.setOnClickListener{
            val sliderItems: MutableList<SliderItem> = ArrayList()
            sliderItems.add(SliderItem(R.drawable.home_c1))
            sliderItems.add(SliderItem(R.drawable.home_c2))
            sliderItems.add(SliderItem(R.drawable.home_c3))
            sliderItems.add(SliderItem(R.drawable.home_c4))
            sliderItems.add(SliderItem(R.drawable.home_c5))

            viewPager2.adapter = SliderAdapter(sliderItems, viewPager2)

            viewPager2.clipToPadding = false
            viewPager2.clipChildren = false
            viewPager2.offscreenPageLimit = 3
            viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(30))
            compositePageTransformer.addTransformer{ page, position ->
                val r = 1- abs(position)
                page.scaleY = 0.85f +r * 0.25f

            }

            viewPager2.setPageTransformer(compositePageTransformer)


            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandler.removeCallbacks(sliderRunnable)
                    sliderHandler.postDelayed(sliderRunnable, 2000)
                }
            })
        }


        viewPager2.adapter = SliderAdapter(sliderItems, viewPager2)

        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(30))
        compositePageTransformer.addTransformer{ page, position ->
            val r = 1- abs(position)
            page.scaleY = 0.85f +r * 0.25f

        }

        viewPager2.setPageTransformer(compositePageTransformer)


        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 2000)
            }
        })

        //작가님 추천
        mListLayout = findViewById(R.id.ListLayout)
        mProfileImage = findViewById(R.id.profile_image)
        mNameText = findViewById(R.id.profile_name)
        mDescText =findViewById(R.id.profile_desc)

        mListLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                val intent = Intent(this@Home, Writer::class.java)

                val Pair1 = Pair.create<View,String>(mProfileImage,"imageTransition")
                val Pair2 = Pair.create<View,String>(mNameText,"nameTransition")
                val Pair3 = Pair.create<View,String>(mDescText,"descTransition")

                var options : ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this@Home,Pair1,Pair2,Pair3)

                startActivity(intent,options.toBundle())

            }
        })


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



    }

    private val sliderRunnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.postDelayed(sliderRunnable, 1000)

    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 1000)
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