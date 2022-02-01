package com.example.myapplication

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class PersonnelReg : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase
    lateinit var btnRegist : Button
    lateinit var edtName : EditText
    lateinit var edtAge : EditText
    lateinit var edgTel : EditText
    lateinit var rg_gender : RadioGroup
    lateinit var rb_gender_m : RadioButton
    lateinit var rb_gender_f : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personnel_reg)

        btnRegist= findViewById(R.id.btnRegister)
        edtName = findViewById(R.id.edtName)
        edtAge = findViewById(R.id.edtAge)
        edgTel = findViewById(R.id.edtTel)
        rg_gender = findViewById(R.id.gender)
        rb_gender_m = findViewById(R.id.male)
        rb_gender_f = findViewById(R.id.female)

        dbManager = DBManager(this,"personnelDB",null,1)

        btnRegist.setOnClickListener{
            var str_name : String = edtName.text.toString()
            var str_age : String = edtAge.text.toString()
            var str_tel : String = edgTel.text.toString()

            var str_gender : String = ""
            if(rg_gender.checkedRadioButtonId == R.id.male){
                str_gender = rb_gender_m.text.toString()
            }
            if(rg_gender.checkedRadioButtonId == R.id.female){
                str_gender = rb_gender_f.text.toString()
            }

            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO personnel VALUES ('" + str_name +"','" +str_gender+"',"+str_age+",'"+str_tel+"')")
            sqlitedb.close()

            var intent = Intent(this, PersonnelInfo::class.java)
            intent.putExtra("intent_name",str_name)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_personnel_reg,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_home -> {
                val intent = Intent(this,Students::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_list -> {
                val intent = Intent(this, PersonnelList::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


}

