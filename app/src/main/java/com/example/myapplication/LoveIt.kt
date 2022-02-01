package com.example.myapplication


import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.myapplication.model.UserData
import com.example.myapplication.view.UserAdapter

class LoveIt : AppCompatActivity() {
    //찜하기 관련 코드입니다.
    private lateinit var addsBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var userList:ArrayList<UserData>
    private lateinit var userAdapter:UserAdapter
    //
    lateinit var go : Button
    //
    /////
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    /////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.love_it)
        /**set List*/
        userList = ArrayList()
        /**set find Id*/
        addsBtn = findViewById(R.id.addingBtn)
        recv = findViewById(R.id.mRecycler)
        /**set Adapter*/
        userAdapter = UserAdapter(this,userList)
        /**setRecycler view Adapter*/
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter
        /**set Dialog*/

        //
        dbManager = DBManager(this,"personnelDB",null,1)
        //

        //
        //얘는 일단 찜해두기로
        addInfo()

        /*
        go = findViewById(R.id.go)
        go.setOnClickListener{
            val intent = Intent(this,Person::class.java)
            startActivity(intent)
        }*/
        //
        //추가하는 기능도 만들자
        addsBtn.setOnClickListener { addInfo2() }

    }

    private fun addInfo() {


        var content = intent.getStringExtra("HisName")
        var thatDay = intent.getStringExtra("HisPhone")

        ////
        sqlitedb = dbManager.readableDatabase
        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM personnel WHERE name = '"+content+"';",null)

        if(cursor.moveToNext()){
            thatDay = cursor.getString((cursor.getColumnIndex("tv_phone"))).toString()
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()


        ///

        if(content != null && thatDay !=null)
        {
            val names = content.toString()
            val number = thatDay.toString()
            userList.add(UserData("Name: $names","Mobile No. : $number"))
            userAdapter.notifyDataSetChanged()
        }
    }
    /**ok now run this */

    private fun addInfo2() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_item,null)
        /**set view*/
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
                dialog,_->
            val names = userName.text.toString()
            val number = userNo.text.toString()
            userList.add(UserData("Name: $names","Mobile No. : $number"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Adding User Information Success",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()

        }
        addDialog.create()
        addDialog.show()
    }
    /**ok now run this */

}