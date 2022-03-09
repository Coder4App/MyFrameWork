package com.cw.myframework

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.alibaba.android.arouter.launcher.ARouter
import com.bytedance.sdk.openadsdk.TTAdConfig
import com.bytedance.sdk.openadsdk.TTAdConstant
import com.bytedance.sdk.openadsdk.TTAdManager
import io.flutter.embedding.android.FlutterActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            //打开一个flutter页面
            startActivity(FlutterActivity.withNewEngine().initialRoute("/").build(this))

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
        TTAdConfig.Builder()
            .appId("1111")
            .useTextureView(true) //使用TextureView控件播放视频,默认为SurfaceView,当有SurfaceView冲突的场景，可以使用TextureView
            .appName("11111")
            .titleBarTheme(TTAdConstant.TITLE_BAR_THEME_DARK)
            .allowShowNotify(true) //是否允许sdk展示通知栏提示
            .allowShowPageWhenScreenLock(true) //是否在锁屏场景支持展示广告落地页
            .debug(true) //测试阶段打开，可以通过日志排查问题，上线时去除该调用
            .directDownloadNetworkType(
                TTAdConstant.NETWORK_STATE_WIFI,
                TTAdConstant.NETWORK_STATE_3G
            ) //允许直接下载的网络状态集合
            .supportMultiProcess(false) //是否支持多进程
            .needClearTaskReset()
            .asyncInit(true) //是否异步初始化sdk,设置为true可以减少SDK初始化耗时
            //.httpStack(new MyOkStack3())//自定义网络库，demo中给出了okhttp3版本的样例，其余请自行开发或者咨询工作人员。
            .build()
    }
}