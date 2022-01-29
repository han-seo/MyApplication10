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

    lateinit var b : Button
    lateinit var bb:Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b = view.findViewById(R.id.go)
        b.setOnClickListener{
            val intent = Intent(getActivity(), Home::class.java)
            startActivity(intent)
        }

        bb = view.findViewById(R.id.goLogin)
        bb.setOnClickListener{
            val intent = Intent(getActivity(), LoginTest::class.java)
            startActivity(intent)
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }






}