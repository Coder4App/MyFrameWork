package com.cw.myframework.testAct.XFermode

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.cw.myframework.R
import kotlinx.android.synthetic.main.activity_x_fermode.*

/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2021/7/1 下午3:58
 *
 * Description:
 */
@Route(path = "/main/TestXFerModeAct")
class TestXFerModeAct : AppCompatActivity(){
    private val pop by lazy {
        TestPop(this@TestXFerModeAct)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_x_fermode)
        xfermode_btn.setOnClickListener {
            pop.showAnchor(xfermode_anchor)
            pop.showAtLocation(xfermode_root,Gravity.CENTER,0,0)
        }
    }
}