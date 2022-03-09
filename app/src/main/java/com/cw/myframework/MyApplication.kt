package com.cw.myframework

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2021/7/1 下午7:52
 *
 * Description:
 */
class MyApplication:Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        initARouter()
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}