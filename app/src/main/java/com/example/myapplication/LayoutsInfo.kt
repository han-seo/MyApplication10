package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.info_layouts.*

//연습파일
//매니페스트 파일 activity 를 dialog로 하려니 안된다. 이것으로 시도
class LayoutsInfo(context:Context) : Dialog(context)  {

    val TAG : String ="로그"
    private var vpAdapter: FragmentStateAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_layouts)

        vpAdapter = CustomPagerAdapter(findViewById(R.id.indicator))
        viewpager2.adapter = vpAdapter

        indicator.setViewPager(viewpager2)

        //배경 투명
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

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