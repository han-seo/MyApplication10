package com.example.myapplication

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item2.*
import kotlinx.android.synthetic.main.list_item2.view.*
import java.io.ByteArrayOutputStream

class RecyclerAdapter(private val items: ArrayList<YoutubeItem>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    //각각의 작가의 정보를 하나의 페이지에 공통적으로 적용시킬수 있도록 하는 코드
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener {it ->
            Toast.makeText(it.context, "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()


            //여기서 데이터베이스 쌓으면 될 것같음
            //아님

        }


        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }

        //
        lateinit var news : Array<String>

        news = arrayOf(
            //헤어
            "‘오늘보다 더 가치있는 내일’을 만들어 드리는 디자이너입니다! 섬세한 손끝 열정으로 고객님의 아름다움, 영화속의 주인공처럼 고객님의 변신을 위해 끊임없는 노력과 소통으로 함께 동행하는 스타일리스트가 되겠습니다.",
            //모델
            "전신 위주의 감성 프로필촬영에 특화 되어있는 일반인 모델입니다. 웨딩 촬영이나 바디 프로필 사진을 찍고 싶습니다,\n"+"다양한 포즈와 자연스러운 분위기 연출로 촬영에 최선을 다하겠습니다. ",
            //스튜디오
            "자연광에서 자연스럽고 감각적인 촬영이 가능한 스튜디오입니다. 저희 스튜디오는 5곳이 넘는 포토존과 촬영을 위한 다양한 소품 및 오브제를 전부 사용하는 스튜디오입니다.\n"+"따뜻한 공간에서 여러분의 소중한 추억을 남겨보세요!",
            //스튜디오
            "아이들이 촬영하기 편하게끔 뽀로로 테마로 예쁘게 꾸며놓은 스튜디오입니다. 아이들을 최우선으로 다양한 소품과 간식거리를 구비하고 있습니다, 아이들의 가장 사랑스러운 순간을 담아내도록 노력할게요!",
            //사진작가
            "안녕하세요.사진촬영은 공장식으로 찍어내기만 하는 방식이 아닌, 본인만의 특별한 매력과 고유한 아름다움을 돋보일 수 있도록 사진의 톤, 포즈, 표정 등 맞춤형으로 촬영합니다. 여러분의 특별한 순간을 기록해보세요!",
            //사진작가
            "안녕하세요, 저는 개발자의 일을 그만두고 본격적으로 인스타그램 게시물 사진작가 일을 시작했어요. 취미로만 하던 사진을 직업으로 삼게되어 누구보다 책임감을 가지고 예쁜 사진을 찍을 자신이 있습니다",
            //모델
            "아직 유명하지는 않지만, 꾸준히 팬션쇼에 참석하고 있는 신입 모델입니다. 더 많은 경험을 기르고 홍보를 하고 싶습니다. \n"+"탈색 헤어 괜찮습니다. 스튜디오는 개인 비용으로 내면 좋겠어요.",
            ////헤어
            "열여덟의 순간, 청담 컬렉션, 웰라쇼 참여 / 다수의 광고 화보 등등 작업한 경우가 많습니다. 능숙한 실력과 경력으로 멋진 사진을 완성시켜드릴게요!",
            //모델
            "모델이 필요한 드라마인 지금 헤어지는 중입니다에 5번 등장한 모델입니다. 전문적이 아닌, 취미사진을 찍고 싶습니다.\n"+" 그다지 전문적이지 않으셔도 됩니다. 초보끼리 같이 멋진 사진 만들어 나가요!",
            //헤어
            "트렌디한 감성추구 손상모 전문 뷰티플래너입니다. 섬세하고 트렌디한 감성으로 고객님의 니즈를 파악하고 디자인해드리겠습니다.더 많은 사진을 보고싶으시면 DM주세요!"
        )

        ////

        ////

        //
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {



                val stream = ByteArrayOutputStream()
                //val bitmap2 = (item.image.getDrawable() as BitmapDrawable).bitmap
                val scale = (1024 / item.image.width.toFloat())
                val image_w = (item.image.width * scale).toInt()
                val image_h = (item.image.height * scale).toInt()
                val resize = Bitmap.createScaledBitmap(item.image, image_w, image_h, true)
                resize.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                val byteArray = stream.toByteArray()

                //정보전달
                val intent = Intent(v?.getContext(), HairOne::class.java)
                intent.putExtra("data1",item.title)
                intent.putExtra("data3",item.content)
                intent.putExtra("data2",byteArray)
                intent.putExtra("data4",item.heart)
                intent.putExtra("news",news[position])
                v?.getContext()?.startActivity(intent)
            }
        })

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item2, parent, false)

        var my_heart = inflatedView.findViewById<TextView>(R.id.how_many)

        inflatedView.like_btn.setOnClickListener{

            //여기서 개수도 늘려야 해
            var flag = true
            if(flag){
                val up = Integer.parseInt(my_heart.text.toString()) +1
                my_heart.setText(""+up)
                flag = false
            }else{
                val up = Integer.parseInt(my_heart.text.toString()) -1
                my_heart.setText(""+up)
                flag = true
            }

            val animator = ValueAnimator.ofFloat(0f,0.5f).setDuration(1000)
            animator.addUpdateListener { animation: ValueAnimator ->
                inflatedView.like_btn.setProgress(
                    animation.getAnimatedValue() as Float
                )
            }
            animator.start()
        }
        //////////////////////


        return RecyclerAdapter.ViewHolder(inflatedView)
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v
        ///
        //private val txtName: TextView = itemView.findViewById(R.id.title)
        //private val txtAge: TextView = itemView.findViewById(R.id.content)
        //private val imgProfile: ImageView = itemView.findViewById(R.id.image)
        ///

        fun bind(listener: View.OnClickListener, item: YoutubeItem) {
            view.thumbnail.setImageBitmap(item.image)
            view.title.text = item.title
            view.content.text = item.content
            view.how_many.text = item.heart.toString()
            view.setOnClickListener(listener)

            /*
            txtName.text = item.title
            txtAge.text = item.content
            Glide.with(itemView).load(item.image).into(imgProfile)



            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val intent = Intent(v?.getContext(), HairOne::class.java)
                    intent.putExtra("data1",item.title)
                    intent.putExtra("data2",item.content)
                    intent.putExtra("data3",item.image)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    v?.getContext()?.startActivity(intent)
                }
            })*/
        }




    }
}