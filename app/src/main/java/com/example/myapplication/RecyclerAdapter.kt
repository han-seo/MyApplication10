package com.example.myapplication

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
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


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener {it ->
            Toast.makeText(it.context, "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()


            //여기다 이제 페이지 이동하면된다.

        }


        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }

        //
        lateinit var news : Array<String>

        news = arrayOf(
            "wht1",
            "wht2",
            "wht3",
            "wht4",
            "wht5",
            "wht6",
            "wht7",
            "wht8",
            "wht9",
            "wht10"

        )

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