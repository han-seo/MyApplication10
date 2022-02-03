package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityAddBinding

import com.example.myapplication.databinding.LoginTestBinding


//로그인 및 회원가입을 하는데 관련한 코드
class LoginTest : AppCompatActivity() {
    lateinit var binding: LoginTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //내가 추가
        //setContentView(R.layout.login_test)

        binding = LoginTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //그리기//////////////////////실험///
        //myCheckPermission(this)
        /*
        binding.addFab.setOnClickListener{
            if(MyApplication.checkAuth()){
                startActivity(Intent(this,AddActivity::class.java))
            }else{
                Toast.makeText(this,"인증을 먼저 진행해 주세요",
                    Toast.LENGTH_SHORT).show()
            }
        }*/
        /////////////////////////////////////
        //상단바 이름 바꾸기
        getSupportActionBar()?.setTitle("로그인 및 회원가입");
        //

    }

    override fun onStart() {
        super.onStart()
        if(!MyApplication.checkAuth()){
            binding.logoutTextView.visibility= View.VISIBLE
            binding.mainRecyclerView.visibility=View.GONE
        }else {
            binding.logoutTextView.visibility= View.GONE
            binding.mainRecyclerView.visibility=View.VISIBLE
            makeRecyclerView()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, AuthActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    /////////////그리기 실험///////////////
    private fun makeRecyclerView(){
        /*
        MyApplication.db.collection("news")

            .get()
            .addOnSuccessListener { result->
                val itemList = mutableListOf<ItemData>()
                for(document in result){
                    val item = document.toObject(ItemData::class.java)
                    item.docId = document.id
                    itemList.add(item)
                }
                binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
                binding.mainRecyclerView.adapter = MyAdapter(this, itemList)
            }
            .addOnFailureListener{
                    exception ->
                Log.d("kkang","Error getting documents: ",exception)
                Toast.makeText(this,"서버로부터 데이터 획득에 실패했습니다.",
                    Toast.LENGTH_SHORT).show()
            }*/
    }


}