package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.MyModel
import kotlinx.android.synthetic.main.card_item.view.* //빌드 그래들에 플러그인 적용한것!
//자료 이거보고 했음 https://www.androidhuman.com/2016-07-25-kotlin_android_extensions

class MyAdapter(private val context: Context, private val myModelArrayList:ArrayList<MyModel>): PagerAdapter() {

    //lateinit var bannerIv: ImageView
    //lateinit var descriptionIv: TextView
    //lateinit var dateIv: TextView
    lateinit var button2 : Button

    override fun getCount(): Int {
        return myModelArrayList.size //return list of records/items
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //inflate layout card_item.xml
        val view = LayoutInflater.from(context).inflate(R.layout.card_item,container,false)

        //get data
        val model= myModelArrayList[position]
        val title = model.title
        val description = model.description
        var date = model.data
        var image = model.image



        //set data to ui views
        //bannerIv = findViewById<ImageView>(R.id.bannerIv)
        view.bannerIv.setImageBitmap(image)
        view.descriptionIv.text = description
        view.dateIv.text = date

        //handle item/card click
        view.setOnClickListener{
            Toast.makeText(context,"$title\n$description\n$date",Toast.LENGTH_SHORT).show()
        }


        //add view to container
        container.addView(view,position)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}