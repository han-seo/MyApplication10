package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.info_layouts.*
import android.view.Window;
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class newTry : AppCompatActivity() {

    //이벤트 구글맵 후기를 뷰페이저2로 볼 수 있는 코드입니다.
    private var vpAdapter: FragmentStateAdapter? = null
    ///드로워 바 틀리면 지우기
    lateinit var toggle : ActionBarDrawerToggle
    ///

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_layouts)


        //상단바 이름 바꾸기
        getSupportActionBar()?.setTitle("스튜디오 후기");
        //

//        vpAdapter = CustomPagerAdapter(supportFragmentManager)
//        viewpager.adapter = vpAdapter
//
//        indicator.setViewPager(viewpager)

        vpAdapter = CustomPagerAdapter(this)
        viewpager2.adapter = vpAdapter

        indicator.setViewPager(viewpager2)

        //배경 투명
        //window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //////////드로워 레이아웃 내용 틀리면 지우기////////


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
//////////////드로워 레이아웃 내용 틀리면 지우기////////
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
                0 -> TestFragment.newInstance(R.drawable.message1, "증명사진은 많이 찍어봤지만 프로필 사진은 처음이라 걱정되고 긴장도 많이 되었는데,\n" +
                        "예약할때부터 친절하게 어떤걸 준비해와야할지, 옷도 함께 고민해주셔서 훨씬 마음 편하게 갔어요 \n")
                1 -> TestFragment.newInstance(R.drawable.message2, "스튜디오 촬영 여러번 해봤지만 여기만큼 친절하고 분위기 예쁘고 결과물 예쁜 곳은 처음이에요\n"+"정말.. 헤어 메이크업 보통 외부에서 받고 와야하는데 여기는 자체적으로 헤메 해주시는 분들이 계셔서 넘 편하고 좋아요!")
                2 -> TestFragment.newInstance(R.drawable.message3, "하나하나 꼼꼼히 봐주셔요 ㅎㅎ 진짜 저 너무 예쁘게 마음에 너무 들게 찍어주셔서 완전 추천입니다!!!")
                3 -> TestFragment.newInstance(R.drawable.message4, "화보를 찍어주셨어요!!!!! 여기 저만찍고싶은곳이라 안알려졌음좋겠는데... 사장님 돈 많이 버셔야하니까 리뷰남겨요")
                else -> TestFragment.newInstance(R.drawable.message1, "메이크업, 헤어 선생님들도 너무 잘하고 좋으시고\n" +
                        "작가님두 전문적이신데 엄청 편하게 잘해셔서 진짜 즐겁게 촬영하고 왔어요! 여기 사진 많이 보고 사진컨셉 마음에 들어서 쵸이스 하게 됐는데 사람들도 좋아서 너무 행복한 작업이었어요\n")
            }
        }
    }

    ////드로워 레이아웃 틀리면 지우기////
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    ////드로워 레이아웃 클리면 지우기////
}