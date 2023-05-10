package com.cw.myframework.testAct.bitmapRegion

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.cw.myframework.R
import kotlinx.android.synthetic.main.activity_bitmap_region.*

@Route(path = "/views/BitmapRegionAct")
class BitmapRegionAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.activity_bitmap_region))
        loadImage()
    }


    fun loadImage() {
        val option1 = BitmapFactory.Options()
        var bitmap:Bitmap? = null

        //设置后只解码图片的宽高，获得的Bitmap为空
        option1.inJustDecodeBounds = true

        bitmap = BitmapFactory.decodeResource(resources,R.drawable.newer_welfare_bag_bottom,option1)

        Log.e("--->","with ${option1.outWidth}  height ${option1.outHeight} bitmap ${bitmap}" )


        val option2 = BitmapFactory.Options()
        option2.inJustDecodeBounds = false
        bitmap = BitmapFactory.decodeResource(resources,R.drawable.newer_welfare_bag_bottom,option2)
        Log.e("--->","with ${bitmap.width}  height ${bitmap.height} bitmap ${bitmap}" )

        im.setImageBitmap(bitmap)
    }
}