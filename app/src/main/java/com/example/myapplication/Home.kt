package com.example.myapplication

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import android.util.Pair


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        hairs = findViewById(R.id.hairs)
        studios = findViewById(R.id.studios)
        models = findViewById(R.id.models)
        cameraman = findViewById(R.id.cameraman)
        goMap = findViewById(R.id.goMap)

        //각 카테고리 클릭릭
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
        sliderItems.add(SliderItem(R.drawable.cat))
        sliderItems.add(SliderItem(R.drawable.hello))
        sliderItems.add(SliderItem(R.drawable.hello2))
        sliderItems.add(SliderItem(R.drawable.hello3))
        sliderItems.add(SliderItem(R.drawable.cat))
        sliderItems.add(SliderItem(R.drawable.hello))

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
                sliderHandler.postDelayed(sliderRunnable, 3000)
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
}