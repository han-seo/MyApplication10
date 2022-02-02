package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.myapplication.databinding.ActivityAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

//파이어베이스 로그인 관련 코드
class AuthActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(MyApplication.checkAuth()){
            changeVisibility("login")
        }else {
            changeVisibility("logout")
        }

        binding.logoutBtn.setOnClickListener {
            //로그아웃...........
            MyApplication.auth.signOut()
            MyApplication.email=null
            changeVisibility("logout")

        }

        binding.goSignInBtn.setOnClickListener{
            changeVisibility("signin")
        }

        binding.googleLoginBtn.setOnClickListener {
            //구글 로그인하기
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            //구글의 인증관리 앱 실행
            val signInIntent = GoogleSignIn.getClient(this, gso).signInIntent
            startActivityForResult(signInIntent, 10)

        }

        binding.signBtn.setOnClickListener {
            //이메일,비밀번호 회원가입........................
            val email: String = binding.authEmailEditView.text.toString()
            val password: String = binding.authPasswordEditView.text.toString()
            //김민혜 추가 코드 실험//
            val name:String = binding.authNameEditView.text.toString()
            val id:String = binding.authIdEditView.text.toString()
            val phone:String = binding.authPhoneEditView.text.toString()
            //김민혜 추가 코드 실험//

            MyApplication.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    binding.authEmailEditView.text.clear()
                    binding.authPasswordEditView.text.clear()
                    //김민혜 추가 코드 실험//
                    binding.authNameEditView.text.clear()
                    binding.authIdEditView.text.clear()
                    binding.authPhoneEditView.text.clear()

                    //받은 데이터 값을 저장하여야 함//

                    //김민혜 추가 코드 실험//

                    if (task.isSuccessful) {
                        // 비밀번호는 최소 6자 이상
                        MyApplication.auth.currentUser?.sendEmailVerification()
                            ?.addOnCompleteListener { sendTask ->
                                if(sendTask.isSuccessful){
                                    Toast.makeText(baseContext, "회원가입에 성공하였습니다. 전송된 메일을 확인해 주세요.",
                                        Toast.LENGTH_SHORT).show()
                                    changeVisibility("logout")
                                    ///가입축하 카톡알림 코드 실험///

                                    val manager = getSystemService(NOTIFICATION_SERVICE)
                                            as NotificationManager
                                    val builder: NotificationCompat.Builder
                                    if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
                                        //26버전 이상
                                        val channelId = "one-channel"
                                        val channelName = "My Channel One"
                                        val channel = NotificationChannel(
                                            channelId,
                                            channelName,
                                            NotificationManager.IMPORTANCE_DEFAULT
                                        ).apply{

                                            description = "My Channel One description"

                                            setShowBadge(true)
                                            val uri: Uri = RingtoneManager.getDefaultUri(
                                                RingtoneManager.TYPE_NOTIFICATION)
                                            val audioAttributes = AudioAttributes.Builder()
                                                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                                                .setUsage(AudioAttributes.USAGE_ALARM)
                                                .build()
                                            setSound(uri,audioAttributes)
                                            enableVibration(true)
                                        }
                                        manager.createNotificationChannel(channel)
                                        builder = NotificationCompat.Builder(this,channelId)
                                    }
                                    else{
                                        builder = NotificationCompat.Builder(this)
                                    }
                                    builder.run{
                                        setSmallIcon(R.drawable.small)
                                        setWhen(System.currentTimeMillis())
                                        setContentTitle("PicKit")
                                        setContentText("가입을 축하드립니다")
                                        setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.big))
                                    }
                                    val KEY_TEXT_REPLY = "key_text_reply"
                                    val replyLabel : String="답장"
                                    var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run{
                                        setLabel(replyLabel)
                                        build()
                                    }
                                    val replyIntent = Intent(this,ReplyReceiver::class.java)
                                    val replyPendingIntent = PendingIntent.getBroadcast(
                                        this,30,replyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
                                    builder.addAction(
                                        NotificationCompat.Action.Builder(
                                            R.drawable.send,
                                            "답장",
                                            replyPendingIntent
                                        ).addRemoteInput(remoteInput).build())
                                    manager.notify(11,builder.build())


                                    ///가입축하 카톡알림 코드 실험///
                                }else {
                                    Toast.makeText(baseContext, "메일 전송 실패",
                                        Toast.LENGTH_SHORT).show()
                                    changeVisibility("logout")
                                }
                            }
                    } else {
                        Toast.makeText(baseContext, "회원가입 실패",
                            Toast.LENGTH_SHORT).show()
                        changeVisibility("logout")
                    }
                }

        }

        binding.loginBtn.setOnClickListener {
            //이메일, 비밀번호 로그인
            val email: String = binding.authEmailEditView.text.toString()
            val password: String = binding.authPasswordEditView.text.toString()
            //김민혜 추가 코드 실험//
            val name:String = binding.authNameEditView.text.toString()
            val id:String = binding.authIdEditView.text.toString()
            val phone:String = binding.authPhoneEditView.text.toString()
            //김민혜 추가 코드 실험//

            MyApplication.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    binding.authEmailEditView.text.clear()
                    binding.authPasswordEditView.text.clear()
                    //김민혜 추가 코드 실험//
                    binding.authNameEditView.text.clear()
                    binding.authIdEditView.text.clear()
                    binding.authPhoneEditView.text.clear()
                    //김민혜 추가 코드 실험//
                    if (task.isSuccessful) {
                        if(MyApplication.checkAuth()){
                            //로그인 성공 상황
                            MyApplication.email=email
                            changeVisibility("login")
                        }else {
                            //발송된 메일로 인증 확인을 안한경우
                            Toast.makeText(baseContext, "전송된 메일로 이메일 인증이 되지 않았습니다.",
                                Toast.LENGTH_SHORT).show()
                        }

                    } else {
                        Toast.makeText(baseContext, "로그인 실패",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //구글 로그인 결과 처리
        if(requestCode==10 ){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!

                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                MyApplication.auth.signInWithCredential(credential)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            //구글 로그인 성공
                            MyApplication.email=account.email
                            changeVisibility("login")
                            //여기서 이제 카톡으로 가입 축하한다고 문제 메시지가 오는 것임

                        } else {
                            //구글 로그인 실패..
                            changeVisibility("logout")
                        }
                    }

            } catch (e: ApiException) {
                changeVisibility("logout")
            }
        }
    }

    fun startPickit(view : View){
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }


    fun changeVisibility(mode: String){
        if(mode === "login"){
            binding.run {
                authMainTextView.text = "${MyApplication.email} 님 반갑습니다."
                logoutBtn.visibility= View.VISIBLE
                startBtn.visibility = View.VISIBLE
                goSignInBtn.visibility= View.GONE
                googleLoginBtn.visibility= View.GONE
                authEmailEditView.visibility= View.GONE
                authPasswordEditView.visibility= View.GONE
                authNameEditView.visibility = View.GONE //김민혜 변경 코드 틀리면 지우기
                authIdEditView.visibility = View.GONE//김민혜 변경 코드 틀리면 지우기
                authPhoneEditView.visibility = View.GONE //김민혜 변경 코드 틀리면 지우기
                signBtn.visibility= View.GONE//김민혜 변경 코드 틀리면 지우기
                loginBtn.visibility= View.GONE//김민혜 변경 코드 틀리면 지우기
            }

        }else if(mode === "logout"){
            binding.run {
                authMainTextView.text = "로그인 하거나 회원가입 해주세요."
                logoutBtn.visibility = View.GONE
                goSignInBtn.visibility = View.VISIBLE
                googleLoginBtn.visibility = View.VISIBLE
                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE
                authNameEditView.visibility = View.GONE//김민혜 변경 코드 틀리면 지우기
                authIdEditView.visibility = View.GONE//김민혜 변경 코드 틀리면 지우기
                authPhoneEditView.visibility = View.GONE//김민혜 변경 코드 틀리면 지우기
                signBtn.visibility = View.GONE//김민혜 변경 코드 틀리면 지우기
                loginBtn.visibility = View.VISIBLE//김민혜 변경 코드 틀리면 지우기
                startBtn.visibility = View.GONE
            }
        }else if(mode === "signin"){
            binding.run {
                logoutBtn.visibility = View.GONE
                goSignInBtn.visibility = View.GONE///////////////////////////////////////////////
                googleLoginBtn.visibility = View.GONE
                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE//김민혜 변경 코드 틀리면 지우기
                authNameEditView.visibility = View.VISIBLE//김민혜 변경 코드 틀리면 지우기
                authIdEditView.visibility = View.VISIBLE//김민혜 변경 코드 틀리면 지우기
                authPhoneEditView.visibility = View.VISIBLE//김민혜 변경 코드 틀리면 지우기
                signBtn.visibility = View.VISIBLE//김민혜 변경 코드 틀리면 지우기
                loginBtn.visibility = View.GONE
                startBtn.visibility = View.GONE
            }
        }
    }
}