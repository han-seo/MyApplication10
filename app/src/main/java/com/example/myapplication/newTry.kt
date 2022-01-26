package com.example.myapplication

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.info_layouts.*
import android.view.Window;

class newTry : AppCompatActivity() {
    private var vpAdapter: FragmentStateAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_layouts)



//        vpAdapter = CustomPagerAdapter(supportFragmentManager)
//        viewpager.adapter = vpAdapter
//
//        indicator.setViewPager(viewpager)

        vpAdapter = CustomPagerAdapter(this)
        viewpager2.adapter = vpAdapter

        indicator.setViewPager(viewpager2)

        //배경 투명
        //window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

//    class CustomPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//        private val PAGENUMBER = 4
//
//        override fun getCount(): Int {
//            return PAGENUMBER
//        }
//
//        override fun getItem(position: Int): Fragment {
//            return when (position) {
//                0 -> TestFragment.newInstance(R.raw.img00, "test 00")
//                1 -> TestFragment.newInstance(R.raw.img01, "test 01")
//                2 -> TestFragment.newInstance(R.raw.img02, "test 02")
//                3 -> TestFragment.newInstance(R.raw.img03, "test 03")
//                else -> TestFragment.newInstance(R.raw.img00, "page00")
//            }
//        }
//    }

    class CustomPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
        private val PAGENUMBER = 4

        override fun getItemCount(): Int {
            return PAGENUMBER
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> TestFragment.newInstance(R.drawable.cat, "test 00")
                1 -> TestFragment.newInstance(R.drawable.dog, "test 01")
                2 -> TestFragment.newInstance(R.drawable.cat, "test 02")
                3 -> TestFragment.newInstance(R.drawable.dog, "test 03")
                else -> TestFragment.newInstance(R.drawable.cat, "page00")
            }
        }
    }
}