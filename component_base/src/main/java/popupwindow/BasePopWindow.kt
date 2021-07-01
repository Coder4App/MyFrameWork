package popupwindow

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.annotation.LayoutRes

/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2021/7/1 下午8:13
 *
 * Description:
 */
abstract class BasePopWindow(private val context: Context) : PopupWindow(context) {
    private var mContentView:View

    init {
        mContentView = LayoutInflater.from(context).inflate(getLayoutId(),null)
        contentView = mContentView
        width = WindowManager.LayoutParams.MATCH_PARENT
        height = WindowManager.LayoutParams.MATCH_PARENT
        setBackgroundDrawable(BitmapDrawable())
        isOutsideTouchable = canOutsideTouchable()
        isFocusable = true
        initView(mContentView)
    }


    abstract fun initView(rootView:View)

    abstract fun canOutsideTouchable(): Boolean

    @LayoutRes
    abstract fun getLayoutId():Int
}