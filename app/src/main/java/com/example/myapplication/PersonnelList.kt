package com.example.myapplication

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView

class PersonnelList : AppCompatActivity() {

    //개인정보를 입력하고 수강생을 신청하는 코드/데이터베이스 관련.
    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase
    lateinit var layout : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personnel_list)

        //상단바 이름 바꾸기
        getSupportActionBar()?.setTitle("개인정보 입력 및 신청");
        //

        dbManager = DBManager(this,"personnelDB",null,1)
        sqlitedb = dbManager.readableDatabase

        layout = findViewById(R.id.personnel)

        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM personnel", null)

        var num : Int =0
        while(cursor.moveToNext()) {

            var str_name = cursor.getString(cursor.getColumnIndex("name")).toString()
            var str_gender = cursor.getString((cursor.getColumnIndex("gender"))).toString()
            var age = cursor.getInt((cursor.getColumnIndex("age")))
            var str_tel = cursor.getString((cursor.getColumnIndex("tel"))).toString()

            var layout_item:LinearLayout = LinearLayout(this)
            layout_item.orientation = LinearLayout.VERTICAL
            layout_item.setPadding(20,10,20,10)
            layout_item.id = num
            layout_item.setTag(str_name)


            var tvName:TextView = TextView(this)
            tvName.text = str_name
            tvName.textSize = 30f
            tvName.setBackgroundColor(Color.LTGRAY)
            layout_item.addView(tvName)

            var tvGender:TextView = TextView(this)
            tvGender.text = str_gender
            layout_item.addView(tvGender)

            var tvAge:TextView = TextView(this)
            tvAge.text = age.toString()
            layout_item.addView(tvAge)

            var tvTel:TextView = TextView(this)
            tvTel.text = str_tel
            layout_item.addView(tvTel)

            layout_item.setOnClickListener{
                val intent = Intent(this,PersonnelInfo::class.java)
                intent.putExtra("intent_name",str_name)
                startActivity(intent)
            }

            layout.addView(layout_item)
            num++;

        }
        cursor.close()
        sqlitedb.close()
        dbManager.close()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_personnel_list,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_home -> {
                val intent = Intent(this,Students::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_reg -> {
                val intent = Intent(this, PersonnelReg::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


}