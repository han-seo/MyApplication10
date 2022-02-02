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
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.models.*
import java.util.*
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.RecyclerView.Adapter

class Models : AppCompatActivity() {

    lateinit var btn : Button
    val list = ArrayList<YoutubeItem>()
    val displayList = ArrayList<YoutubeItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.studios)


        //카테고리 모델 관련 코드입니다.
        val resources: Resources = this.resources
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.image01)

        ////드로어에이블 말고 비트맵으로 바꿔봄, 갤러리에서 사진 가져오도록, 틀리면 원상복귀 필요
        list.add(YoutubeItem(bitmap2!!,"토토로", getString(R.string.title01),"212"))
        list.add(YoutubeItem(bitmap2!!,"토토로", getString(R.string.title02),"33"))
        list.add(YoutubeItem(bitmap2!!,"토토로", getString(R.string.title03),"56"))
        list.add(YoutubeItem(bitmap2!!,"토토로", getString(R.string.title04),"99"))
        list.add(YoutubeItem(bitmap2!!,"토토로", getString(R.string.title05),"872"))
        list.add(YoutubeItem(bitmap2!!,"토토로", getString(R.string.title06),"534"))
        list.add(YoutubeItem(bitmap2!!,"토토로", getString(R.string.title07),"23"))
        list.add(YoutubeItem(bitmap2!!,"토토로", getString(R.string.title08),"1"))
        list.add(YoutubeItem(bitmap2!!,"토토로", getString(R.string.title09),"56"))
        list.add(YoutubeItem(bitmap2!!,"토토로", getString(R.string.title10),"69"))


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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}