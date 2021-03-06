package com.example.myapplication


import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.myapplication.model.UserData
import com.example.myapplication.view.UserAdapter
import com.google.android.material.navigation.NavigationView

class LoveIt : AppCompatActivity() {
    //찜하기 관련 코드
    private lateinit var addsBtn:FloatingActionButton
    private lateinit var recv:RecyclerView
    private lateinit var userList:ArrayList<UserData>
    private lateinit var userAdapter:UserAdapter
    //
    lateinit var go : Button
    //
    /////
    lateinit var dbManager: DBManager2
    lateinit var sqlitedb : SQLiteDatabase
    lateinit var str_name : String
    lateinit var str_tel : String
    lateinit var layout : RecyclerView
    /////

    ///드로워 바//
    lateinit var toggle : ActionBarDrawerToggle
    ///
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.love_it)

        //상단바 이름 바꾸기
        getSupportActionBar()?.setTitle("스크랩하기(찜하기)");
        //
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

        //////////드로워 레이아웃////////


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
//////////////드로워 레이아웃////////

    }

    private fun addInfo() {


        var content = intent.getStringExtra("HisName")
        var thatDay = intent.getStringExtra("HisPhone")


        ////올때마다 저장해두자.
        str_name= intent.getStringExtra("intent_name2").toString()
        dbManager = DBManager2(this,"personnelDB2",null,1)
        sqlitedb = dbManager.readableDatabase
        //layout = findViewById(R.id.personnel2)

        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM personnel2 WHERE name = '"+str_name+"';",null)

        if(cursor.moveToNext()){

            str_tel = cursor.getString((cursor.getColumnIndex("tel"))).toString()

        }
        cursor.close()
        sqlitedb.close()
        dbManager.close()
        /*
        if(cursor.moveToNext()){
            thatDay = cursor.getString((cursor.getColumnIndex("tv_phone"))).toString()
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()*/

        userList.add(UserData("Name: 강동원 모델","소개 : 크리스마스 컨셉으로 사진을 찍고 싶습니다"))
        userAdapter.notifyDataSetChanged()


        ///2번째 시도

        /*layout = findViewById(R.id.mRecycler)
        var cursor2 : Cursor
        cursor2 = sqlitedb.rawQuery("SELECT * FROM personnel2", null)

        var num : Int =0
        while(cursor2.moveToNext()) {

            var str_name = cursor2.getString(cursor2.getColumnIndex("name")).toString()
            var str_tel = cursor2.getString((cursor2.getColumnIndex("tel"))).toString()

            var layout_item:RecyclerView = RecyclerView(this)
            layout_item.id = num
            layout_item.setTag(str_name)


            //var tvName:TextView = TextView(this)
            //tvName.text = str_name
            //tvName.textSize = 30f
            //tvName.setBackgroundColor(Color.LTGRAY)
            //layout_item.addView(tvName)


            //var tvTel:TextView = TextView(this)
            //tvTel.text = str_tel
            //ayout_item.addView(tvTel)

            //layout_item.setOnClickListener{
            //val intent = Intent(this,PersonnelInfo::class.java)
            //intent.putExtra("intent_name",str_name)
            //startActivity(intent)
            //}

            //layout.addView(layout_item)
            userList.add(UserData("Name: $str_name","Mobile No. : $str_tel"))
            userAdapter.notifyDataSetChanged()
            num++;

        }*/
        ///2번째 시도

        if(content != null && thatDay !=null)
        {
            val names = content.toString()
            val number = thatDay.toString()
            userList.add(UserData("Name: $names","소개. : $number"))
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
            userList.add(UserData("Name: $names","소개. : $number"))
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

    ////드로워 레이아웃////
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    ////드로워 레이아웃////

}