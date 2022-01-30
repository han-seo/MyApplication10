package com.example.myapplication

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.mypage_dialog.view.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class MyPage : AppCompatActivity() {

    lateinit var editBtn : Button
    ///여기는 사진찍기 변수///
    val CAMERA_PERMISSION= arrayOf(android.Manifest.permission.CAMERA)
    val FLAG_PERM_CAMERA = 98

    //lateinit var binding: ActivityMainBinding
    lateinit var filePath: String

    lateinit var userImageView: ImageView
    ///여기는 사진찍기 변수///

    ///여기는 디데이변수///
    lateinit var calender_btn: Button
    lateinit var tv_result: TextView
    ///여기는 디데이변수///

    ///여기는 스와이프 변수///
    //actionbar
    private lateinit var actionBar: ActionBar
    private lateinit var myModelList:ArrayList<MyModel>
    private lateinit var myAdapter:MyAdapter
    private lateinit var viewPager: ViewPager
    ///여기는 스와이프 변수///

    ////여기는 나의 활동 편집 변수////
    lateinit var editAlbum: ImageView
    ////여기는 나의 활동 편집 변수////

    ////시험용 곧 지울것///
    lateinit var button4 : Button
    ///시럼용 곧 지울것///


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage)

        editBtn = findViewById(R.id.editbtn)

        userImageView = findViewById(R.id.userImageView)

        ///스와이프///
        actionBar = this.supportActionBar!!
        viewPager = findViewById<ViewPager>(R.id.viewPager)
        ///나의 활동 편집
        var title = intent.getStringExtra("title")
        var content = intent.getStringExtra("content")
        var thatDay = intent.getStringExtra("thatDay")

        loadCards()
        ///여기는 스와이프 내용///

        editBtn.setOnClickListener {

            val mDialogView = LayoutInflater.from(this).inflate(R.layout.mypage_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("프로필 사진 설정하기")

            val mAlertDialog = mBuilder.show()
            mDialogView.galleryButtons.setOnClickListener {

                Toast.makeText(this, "Image Button Clicked", Toast.LENGTH_SHORT).show()
                ///
                //gallery app........................
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                intent.type = "image/*"
                startActivityForResult(intent, 10)
                mAlertDialog.dismiss()

                ///
            }

            mDialogView.cameraButtons.setOnClickListener {

                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val file = File.createTempFile(
                    "JPEG_${timeStamp}_",
                    ".jpg",
                    storageDir
                )
                filePath = file.absolutePath

                val photoURI: Uri = FileProvider.getUriForFile(
                    this,
                    "com.example.myapplication.fileprovider", file
                )

                //고친 부분, 구글링 코드는 퍼미션부분에 문제가 있었던 듯함.
                if(isPermitted(CAMERA_PERMISSION)){
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(intent, 20)
                }else {
                    ActivityCompat.requestPermissions(this, CAMERA_PERMISSION, FLAG_PERM_CAMERA)
                }
                mAlertDialog.dismiss()
            }
        }

        ///디데이용 변수///
        tv_result = findViewById(R.id.tv_result)
        calender_btn = findViewById(R.id.calender_button)
        ///디데이용 변수///


        ////시험용, 수정 필요///
        //
        //
        button4 = findViewById(R.id.button4)
        button4.setOnClickListener{


            val stream = ByteArrayOutputStream()
            val bitmap2 = (userImageView.getDrawable() as BitmapDrawable).bitmap
            val scale = (1024 / bitmap2.width.toFloat())
            val image_w = (bitmap2.width * scale).toInt()
            val image_h = (bitmap2.height * scale).toInt()
            val resize = Bitmap.createScaledBitmap(bitmap2, image_w, image_h, true)
            resize.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val byteArray = stream.toByteArray()

            val intent = Intent(this, CheckHoXi::class.java)
            //이거를 액티비티를 풀 받아서 필요한 액티비티에 모두 연결해 드린다.

            intent.putExtra("image", byteArray)

            startActivity(intent)

        }

    }

    //나의 활동 갤러리로 이동할 수 있는 코드다.
    fun editIt(view: View){
        var intent = Intent(this, EditAlbum::class.java)
        startActivity(intent)
    }

    //예약 확인하기로 이동할 수 있는 코드다.
    fun confirmMyInfo(view: View){
        var intent = Intent(this, CheckReservation::class.java)
        startActivity(intent)

    }

    //나의 정보 확인하기로 이동할 수 있는 코드다.
    fun checkReservation(view: View){
        var intent = Intent(this,ConfirmMyInfo::class.java)
        startActivity(intent)

    }

    ///디데이를 계산해주는 코드다
    fun selectDate(view: View){
        var c = Calendar.getInstance()
        var cYear = c.get(Calendar.YEAR)
        var cMonth = c.get(Calendar.MONTH)
        var cDay = c.get(Calendar.DAY_OF_MONTH)

        val calendarDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                cDay = dayOfMonth
                cMonth = month
                cYear = year

                val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
                val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                val age =
                    ((cDay * 86400 * 100 + cMonth * 30 * 86400 * 100 + cYear * 365 * 86400 * 100 - currentDay * 86400 * 100 - currentMonth * 30 * 86400 * 100 - currentYear * 365 * 86400 * 100) / 100) / 86400
                tv_result.setText("" + age)

            }, cYear, cMonth, cDay
        )
        calendarDialog.show()

    }


    //내가 카메라를 사용해도 되는지 권리를 물어보는 부분이다.
    fun isPermitted(permissions: Array<String>): Boolean{
        for(permission in permissions){
            val result = ContextCompat.checkSelfPermission(this, permission)
            if(result != PackageManager.PERMISSION_GRANTED){
                return false
            }
        }
        return true
    }

    //선택한 갤러리 사진을 원하는 사이즈로 맞추어준다.
    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = contentResolver.openInputStream(fileUri)

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    //카메라 앱을실행시켜려 할 때 startactivity와 같이 실행되는 코드이다.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 10 && resultCode === Activity.RESULT_OK) {
            //갤러리 버튼을 눌러서 사진을 선택했으니 내가 원하는 규격으로 사진을 조정해준다.
            try {
                //inSampleSize 비율 계산, 지정
                val calRatio = calculateInSampleSize(
                    data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                //이미지 로딩
                var inputStream = contentResolver.openInputStream(data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null
                bitmap?.let {
                    userImageView.setImageBitmap(bitmap)
                } ?: let {
                    Log.d("kkang", "bitmap null.............")
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }

        } else if (requestCode === 20 && resultCode === Activity.RESULT_OK) {
            //카메라 앱을 켰을때
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            //유저가 찍은 사진으로 나타날 것임
            bitmap?.let {
                userImageView.setImageBitmap(bitmap)
            }
        }
    }

    //내가 더한 부분 , 퍼미션이 이어지도록 고쳐봤다.
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            FLAG_PERM_CAMERA -> {
                var checked = true
                for (grant in grantResults) {
                    if (grant != PackageManager.PERMISSION_GRANTED) {
                        checked = false
                        break
                    }
                }
                if (checked) {

                    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                    val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    val file = File.createTempFile(
                        "JPEG_${timeStamp}_",
                        ".jpg",
                        storageDir
                    )
                    filePath = file.absolutePath


                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.example.myapplication.fileprovider", file
                    )

                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(intent, 20)
                }
            }
        }
    }

    //나의 활동 갤러리 보여주기 및 갱신 코드이다. drawable로 하려다가 bitmap으로 바꾸었다.
    private fun loadCards() {
        //init list
        myModelList = ArrayList()
        viewPager = findViewById<ViewPager>(R.id.viewPager)

        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val thatDay = intent.getStringExtra("thatDay")


        val resources: Resources = this.resources
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.cat)


        if(title != null && content !=null && thatDay!=null)
        {
            Toast.makeText(this, "$title", Toast.LENGTH_SHORT).show()
            //사진을 비트맵형식으로 받아온다.
            val byteArray = intent.getByteArrayExtra("image")
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

            myModelList.add(//이것은 내가 직접 추가하는 사진첩의 코드이다.
                MyModel(
                    "$title",
                    "$content",
                    "$thatDay",
                    bitmap // 받는것도 drawable 속성이 아닌 비트맵으로 받도록 바꾸었다.
                )
            )
        }
        //add items/data to list
        myModelList.add(
            MyModel(
                "제목1",
                "내용1입니다. 과연 어떤 내용일까요 무슨말이라도 꺼내야 하는데 내용을 채워야 하는데",
                "2022년 5월 8일",
                bitmap2
            )
        )

        myModelList.add(
            MyModel(
                "제목2",
                "내용2입니다. 저는 어제 고기를 먹었어요 고기는 참 맛있지요",
                "2022년 8월 9일",
                bitmap2
            )
        )

        myModelList.add(
            MyModel(
                "제목3",
                "내용 3입니다. 마이페이지는 여기에 있습니다. 마이페이지가 여기에 있다구요",
                "2023년 8월 30일",
                bitmap2
            )
        )

        //setup adapter
        myAdapter= MyAdapter(this, myModelList)
        //set adapter to viewPager
        viewPager.adapter = myAdapter
        //set default padding
        viewPager.setPadding(100, 0, 100, 0)
    }

}
