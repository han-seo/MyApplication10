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
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.airbnb.lottie.LottieAnimationView
import com.example.myapplication.databinding.ActivityAuthBinding
import com.example.myapplication.databinding.ListItem2Binding
import kotlinx.android.synthetic.main.list_item2.*

class Hairs : AppCompatActivity() {

    lateinit var btn : Button
    val list = ArrayList<YoutubeItem>()
    val displayList = ArrayList<YoutubeItem>()
    lateinit var like_btn : LottieAnimationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hairs)

        loadCard()

        btn = findViewById(R.id.btn)

        btn.setOnClickListener{
            val intent = Intent(this, EditList::class.java)
            startActivity(intent)
        }

        ///
        //좋아요 버튼 클릭 리스너





    }

    private fun loadCard(){

        val resources: Resources = this.resources
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.image01)

        list.add(YoutubeItem(bitmap2!!, "토토로",getString(R.string.title01),"120"))
        list.add(YoutubeItem(bitmap2!!, "토토로",getString(R.string.title02),"231"))
        list.add(YoutubeItem(bitmap2!!, "토토로",getString(R.string.title03),"4"))
        list.add(YoutubeItem(bitmap2!!, "토토로",getString(R.string.title04),"45"))
        list.add(YoutubeItem(bitmap2!!, "토토로",getString(R.string.title05),"500"))
        list.add(YoutubeItem(bitmap2!!, "토토로",getString(R.string.title06),"128"))
        list.add(YoutubeItem(bitmap2!!, "토토로",getString(R.string.title07),"322"))
        list.add(YoutubeItem(bitmap2!!, "토토로",getString(R.string.title08),"78"))
        list.add(YoutubeItem(bitmap2!!, "토토로",getString(R.string.title09),"54"))
        list.add(YoutubeItem(bitmap2!!, "토토로",getString(R.string.title10),"111"))

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

    }


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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
