package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class HomeFragment : Fragment() {

    lateinit var b : Button
    lateinit var c : Button



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b = view.findViewById(R.id.go)
        c = view.findViewById(R.id.btn_start)

        b.setOnClickListener {
            val intent = Intent(getActivity(), Home::class.java)
            startActivity(intent)

        }

        c.setOnClickListener {
            var intent = Intent(getActivity(), Login::class.java)
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

