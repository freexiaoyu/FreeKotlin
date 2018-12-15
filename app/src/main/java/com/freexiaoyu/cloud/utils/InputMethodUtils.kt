package com.freexiaoyu.cloud.utils

import android.content.Context
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager

object InputMethodUtils {
    /** 显示软键盘  */
    fun showInputMethod(view: View) {
        val imm = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    /** 显示软键盘  */
    fun showInputMethod(context: Context) {
        val imm = context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    /** 多少时间后显示软键盘  */
    fun showInputMethod(view: View, delayMillis: Long) {
        // 显示输入法
        Handler().postDelayed({ InputMethodUtils.showInputMethod(view) }, delayMillis)
    }
}
