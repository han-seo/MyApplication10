package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class bar :AppCompatActivity() {



    private val fl: FrameLayout by lazy {
        findViewById(R.id.fl_)
    }
    private val bn: BottomNavigationView by lazy {
        findViewById(R.id.bn_)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)
        supportFragmentManager.beginTransaction().add(fl.id, Fragment() ).commit()
        bn.setOnNavigationItemSelectedListener {
            replaceFragment(
               when (it.itemId)
                {
                    R.id.menu_home -> Home()
                   R.id.menu_love -> LoveIt()
                    R.id.menu_category -> Home()
                    R.id.menu_mypage -> MyPage()
                    else -> newUpload()
                }
            )
            true
        }
    }

    private fun replaceFragment(fragment: AppCompatActivity) {
        supportFragmentManager.beginTransaction().replace(fl.id, Fragment() ).commit()
    }
}



