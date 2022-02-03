package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController


class SplashFragment : Fragment() {


    //로딩화면과 관련된 코드. 픽킷의 로고가 3초동안 뜬다.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler().postDelayed({
            if(onBoardingFinished()){
                findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }

        },3000)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

        return sharedPref.getBoolean("Finished",false)
    }

}

