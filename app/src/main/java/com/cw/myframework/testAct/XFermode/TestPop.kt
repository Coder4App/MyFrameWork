package com.cw.myframework.testAct.XFermode

import android.content.Context
import android.view.View
import com.cw.myframework.R
import popupwindow.BasePopWindow

/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2021/7/1 下午8:33
 *
 * Description:
 */
class TestPop(context: Context):BasePopWindow(context) {

    override fun initView(rootView: View) {

    }

    override fun canOutsideTouchable(): Boolean {
        return true
    }

    override fun getLayoutId(): Int {
       return R.layout.pop_x_fermode
    }

    fun showAnchor(xfermodeAnchor: View) {
        val guideLoc = IntArray(2)
        //
        xfermodeAnchor.getLocationOnScreen(guideLoc)
        val targetWidth = xfermodeAnchor.measuredWidth
        val targetHeight = xfermodeAnchor.measuredHeight






    }
}