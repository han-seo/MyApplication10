package com.example.myapplication

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.hair_one.*

class CheckReservation: AppCompatActivity() {
    private val swipeRefreshLayout : SwipeRefreshLayout by lazy {
        findViewById(R.id.swipeRefreshLayout)
    }

    private val recyclerView : RecyclerView by lazy {
        findViewById(R.id.recyclerView)
    }

    private val adapter = Adapter()
    private val layoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_reservation)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false

            ///내가 바꾼부분///
            val list = mutableListOf<String>()
            //val resources: Resources = this.resources
            //val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.image01)


            ///내가 바꾼부분///
            list.add("하쿠 작가님")
            list.add("뽀로로 작가님")
            list.add("마루밑 아리에티 작가님")

            //받은정보를추가시키기
            var title = intent.getStringExtra("title_hey")
            if(title!=null){
                list.add(title)
            }

            adapter.reload(list)
        }

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        ///내가 바꾼부분///
        val list = mutableListOf<String>()
        list.add("하쿠 작가님")
        list.add("뽀로로 작가님")
        list.add("마루밑 아리에티 작가님")

        //받은정보를추가시키기
        var title = intent.getStringExtra("title_hey")
        if(title!=null){
            list.add(title)
        }

        adapter.reload(list)
        setItemTouchHelper()
    }
    private fun setItemTouchHelper(){

        ItemTouchHelper(object : ItemTouchHelper.Callback() {

            private val limitScrollX = dipToPx(100f,this@CheckReservation)
            private var currentScrollX = 0
            private var currentScrollXWhenInActive =0
            private var initXWhenInActive = 0f
            private var firstInActive = false

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = 0
                val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                return makeMovementFlags(dragFlags,swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
            override fun getSwipeThreshold(viewHolder : RecyclerView.ViewHolder):Float {
                return Integer.MAX_VALUE.toFloat()
            }

            override fun getSwipeEscapeVelocity(defaultValue: Float) : Float {
                return Integer.MAX_VALUE.toFloat()
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    if(dX == 0f) {
                        currentScrollX = viewHolder.itemView.scrollX
                        firstInActive = true
                    }

                    if(isCurrentlyActive){
                        var scrollOffset = currentScrollX + (-dX).toInt()
                        if(scrollOffset > limitScrollX) {
                            scrollOffset = limitScrollX
                        }
                        else if(scrollOffset < 0){
                            scrollOffset = 0
                        }

                        viewHolder.itemView.scrollTo(scrollOffset, 0)
                    }
                    else{

                        if(firstInActive){
                            firstInActive = false
                            currentScrollXWhenInActive = viewHolder.itemView.scrollX
                            initXWhenInActive = dX
                        }

                        if(viewHolder.itemView.scrollX < limitScrollX) {
                            viewHolder.itemView.scrollTo((currentScrollXWhenInActive * dX / initXWhenInActive).toInt(),0)
                        }

                    }
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)

                if(viewHolder.itemView.scrollX > limitScrollX){
                    viewHolder.itemView.scrollTo(limitScrollX,0)
                }else if(viewHolder.itemView.scrollX < 0){
                    viewHolder.itemView.scrollTo(0,0)
                }
            }

        }).apply {
            attachToRecyclerView(recyclerView)
        }

    }

    private fun dipToPx(dipValue:Float,context: Context): Int {
        return (dipValue * context.resources.displayMetrics.density).toInt()

    }
}