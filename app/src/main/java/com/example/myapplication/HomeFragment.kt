package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

class HomeFragment : Fragment() {

    //메인 홈이 나타나기 전 코드. 회원가입을 하지 않으셨군요! 하고 알려주는 페이지로 사용될 것.
    lateinit var b : Button
    /*
    lateinit var bb:Button
    lateinit var bbb:Button
    lateinit var bbbb:Button
    lateinit var bbbbb:Button
    //lateinit var bbbbbb:Button*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        b = view.findViewById(R.id.button5)
        b.setOnClickListener{
            val intent = Intent(getActivity(), LoginTest::class.java)
            startActivity(intent)
        }
/*
        bb = view.findViewById(R.id.goLogin)
        bb.setOnClickListener{
            val intent = Intent(getActivity(), LoginTest::class.java)
            startActivity(intent)
        }

        bbb = view.findViewById(R.id.mypageg)
        bbb.setOnClickListener{
            val intent = Intent(getActivity(), LoveIt::class.java)
            startActivity(intent)
        }

        bbbb = view.findViewById(R.id.loveit)
        bbbb.setOnClickListener{
            val intent = Intent(getActivity(), MyPage::class.java)
            startActivity(intent)
        }

        bbbbb = view.findViewById(R.id.drawers)
        bbbbb.setOnClickListener{
            val intent = Intent(getActivity(), category::class.java)
            startActivity(intent)
        }*/

        /*bbbbbb = view.findViewById(R.id.bar)
        bbbbbb.setOnClickListener{
            val intent = Intent(getActivity(), bar::class.java)
            startActivity(intent)
        }*/


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }






}