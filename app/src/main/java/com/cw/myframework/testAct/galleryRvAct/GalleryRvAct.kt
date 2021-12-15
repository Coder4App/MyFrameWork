package com.cw.myframework.testAct.galleryRvAct

import android.content.Context
import android.icu.util.UniversalTimeScale.MAX_SCALE
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.cw.myframework.R
import kotlinx.android.synthetic.main.activity_gallery_recycler.*
import kotlin.math.abs


/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2021/8/9 下午7:23
 *
 * Description: 画廊效果的RecyclerView
 */
@Route(path = "/views/GalleryRvAct")
class GalleryRvAct : AppCompatActivity() {
    private val mAdapter: GalleryRvAdapter by lazy {
        GalleryRvAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_recycler)
        initView()
        test()
    }

    private fun test() {
        var test1 = application.getSystemService(Context.WINDOW_SERVICE)

        var test2 = application.getSystemService(Context.WINDOW_SERVICE)

        var test3 = getSystemService(Context.WINDOW_SERVICE)

        print("--->")
    }


    private var mCurrentItemPos:Int = 0
    private var mCurrentItemOffset:Int = 0
    private val MAX_SCALE = 1.0
    private val MIN_SCALE = 0.9
    val pagerSnapHelper = PagerSnapHelper()
    private fun initView() {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        //让rv 按页滑动 一个官方的库

        pagerSnapHelper.attachToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                mCurrentItemOffset += dx
                Log.e("----->","dx = $dx dy = $dy mCurrentItemOffset = $mCurrentItemOffset")
                val childCount = recyclerView.childCount
                for (i in 0 until childCount) {
                    val child: View = recyclerView.getChildAt(i)
                    val left: Int = child.left
                    val paddingStart = recyclerView.paddingStart
                    // 遍历recyclerView子项，以中间项左侧偏移量为基准进行缩放
                    val bl =
                        1f.coerceAtMost(abs(left - paddingStart) * 1f / child.width)
                    val scale: Float = (MAX_SCALE - bl * (MAX_SCALE - MIN_SCALE)).toFloat()
                    val alpha: Float = (1 - bl * (1 - 0.3)).toFloat()
                    child.scaleY = scale
                    child.alpha = alpha
                }

                if (dx!= 0){
                    val center = pagerSnapHelper.findTargetSnapPosition(recyclerView.layoutManager,dx,dy)
                    Log.e("----->","center Item = ${center}")
                }
                Log.e("----->","newState = ${recyclerView.scrollState}")
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == 0){
                }
            }
        })

        mAdapter.notifyDataSetChanged()


        recyclerView.postDelayed({
            recyclerView.smoothScrollToPosition(1)
        },5000)

    }
}