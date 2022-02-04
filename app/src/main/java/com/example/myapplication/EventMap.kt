package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.info_layout.*
import kotlinx.android.synthetic.main.mypage_dialog.view.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//이벤트용 구글맵과 관련된 코드, 할인하는 스튜디오 지점을 표시해주고 내 위치도 표시해서 위치 파악 가능하도록 할 것임
//,GoogleMap.OnMarkerClickListener
class EventMap : AppCompatActivity(), OnMapReadyCallback, GoogleMap.InfoWindowAdapter,
    GoogleMap.OnInfoWindowClickListener {

    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_map)

        //상단바 이름 바꾸기
        getSupportActionBar()?.setTitle("오늘의 반짝 할인 스튜디오");
        //

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

    }



    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        // 37.557667, 126.926546 홍대입구역
        val latLng = LatLng(37.557667, 126.926546)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15f))
        val markerOptions = MarkerOptions().position(latLng).title("홍대입구역")
        googleMap.addMarker(markerOptions)
        ////내가 추가하는 코드

        //googleMap.setOnMarkerClickListener(this)
        googleMap.setInfoWindowAdapter(this)
        googleMap.setOnInfoWindowClickListener(this)
        //인포윈도우를 클릭하면 또다른 이벤트가 일어나도록 설정


        ////
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.isMyLocationEnabled = true
        } else {
            checkLocationPermissionWithRationale()
        }
    }

    private fun checkLocationPermissionWithRationale() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder(this)
                    .setTitle("위치정보")
                    .setMessage("이 앱을 사용하기 위해서는 위치정보에 접근이 필요합니다. 위치정보 접근을 허용하여 주세요.")
                    .setPositiveButton("확인") { dialogInterface, i -> ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSIONS_REQUEST_LOCATION) }.create().show()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSIONS_REQUEST_LOCATION)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        googleMap!!.isMyLocationEnabled = true
                    }
                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }

    companion object {
        const val MY_PERMISSIONS_REQUEST_LOCATION = 99
    }

    override fun onInfoWindowClick(marker: Marker) {
        Toast.makeText(
            this, "Info window clicked",
            Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(this, newTry::class.java)
        startActivity(intent)
    }

    ///////////////////////////////////////////////////////////////////

    override fun getInfoContents(marker: Marker): View {
        var mWindow = layoutInflater.inflate(R.layout.info_layout, null,false)

        /*button5.setOnClickListener{
            val intent = Intent(this, newTry::class.java)
            startActivity(intent)
        }*/
        return mWindow
    }

    //인포윈도우로 해당 스튜디오의 정보를 알려준다.
    override fun getInfoWindow(marker: Marker): View? {
        var mWindow = layoutInflater.inflate(R.layout.info_layout, null,false)
        /*button5.setOnClickListener{
            val intent = Intent(this, newTry::class.java)
            startActivity(intent)
        }*/
        return mWindow
    }


    /*
    override fun onMarkerClick(p0: Marker): Boolean {
        //팝업띄우기

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.info_layout, null)
        return true
    }*/


}