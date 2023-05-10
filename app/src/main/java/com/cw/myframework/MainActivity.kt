package com.cw.myframework

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.Engine
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.cw.myframework.viewmodel.MainVM
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import util.QrCodeUtil
import java.io.File

//import io.flutter.embedding.android.FlutterActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            //打开一个flutter页面
//            startActivity(FlutterActivity.withNewEngine().initialRoute("/").build(this))
//            ARouter.getInstance().build("/views/BitmapRegionAct").navigation();
//            Glide.with(im).asBitmap().load("https://qiniu-app.qtshe.com/123ff.png").into(im)
            Glide.with(this).load("https://qiniu-app.qtshe.com/123ff.png").into(im)
//            Glide.with(this).downloadOnly().override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL).load("https://qiniu-app.qtshe.com/123ff.png").into(object:CustomTarget<File>(){
//                override fun onResourceReady(resource: File, transition: Transition<in File>?) {
//                     val  thred = Thread.currentThread();
//                    val ss = resource
//                    Log.v("---->","finish ${thred}")
//                    QrCodeUtil.getDecodeAbleBitmap(ss.absolutePath)
//                    im.setImageBitmap(QrCodeUtil.getDecodeAbleBitmap(ss.absolutePath));
//                }
//
//                override fun onLoadCleared(placeholder: Drawable?) {
//                    TODO("Not yet implemented")
//                }
//
//            })

        }

        init()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun init(){
//        Log.e("---->","1 ${Thread.currentThread()}")
//        GlobalScope.launch(Dispatchers.IO){
//            Log.e("---->","2 ${Thread.currentThread()}")
//            withContext(Dispatchers.IO) {
//                //模拟网络获取
//                Log.e("---->","3 ${Thread.currentThread()}")
//                Thread.sleep(2000)
//                Log.e("---->","4 ${Thread.currentThread()}")
//            }
//            withContext(Dispatchers.IO) {
//                //模拟网络获取
//                Log.e("---->","5 ${Thread.currentThread()}")
//                Thread.sleep(2000)
//                Log.e("---->","6 ${Thread.currentThread()}")
//            }
//            Log.e("---->","7 ${Thread.currentThread()}")
//        }
//        Log.e("---->","8 ${Thread.currentThread()}")

        Log.e("---->","1 ${Thread.currentThread()}")
//        runBlocking(Dispatchers.IO) {
//            Log.e("---->","2 ${Thread.currentThread()}")
//        }
        GlobalScope.launch(Dispatchers.Main) {
            Log.e("---->","2 ${Thread.currentThread()}")
        }
        Log.e("---->","3 ${Thread.currentThread()}")

        lifecycleScope
    }

}