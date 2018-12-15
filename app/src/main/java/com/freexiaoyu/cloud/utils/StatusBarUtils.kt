package com.freexiaoyu.cloud.utils

import android.app.Activity
import android.view.Window
import android.view.WindowManager

import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * Created by DIY on 2017/5/25. 17:44
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

object StatusBarUtils {
    /***
     * 设置状态栏颜色
     * @param activity 当前activity
     * @param darkmode true设置黑色 false不设置
     * @return
     */
    fun setMiuiStatusBarDarkMode(activity: Activity, darkmode: Boolean): Boolean {
        val clazz = activity.window.javaClass
        try {
            var darkModeFlag = 0
            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            darkModeFlag = field.getInt(layoutParams)
            val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
            extraFlagField.invoke(activity.window, if (darkmode) darkModeFlag else 0, darkModeFlag)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

    /***
     * 设置状态栏颜色
     * @param activity 当前activity
     * @param dark true设置黑色 false不设置
     * @return
     */
    fun setMeizuStatusBarDarkIcon(activity: Activity?, dark: Boolean): Boolean {
        var result = false
        if (activity != null) {
            try {
                val lp = activity.window.attributes
                val darkFlag = WindowManager.LayoutParams::class.java
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                val meizuFlags = WindowManager.LayoutParams::class.java
                        .getDeclaredField("meizuFlags")
                darkFlag.isAccessible = true
                meizuFlags.isAccessible = true
                val bit = darkFlag.getInt(null)
                var value = meizuFlags.getInt(lp)
                if (dark) {
                    value = value or bit
                } else {
                    value = value and bit.inv()
                }
                meizuFlags.setInt(lp, value)
                activity.window.attributes = lp
                result = true
            } catch (e: Exception) {
            }

        }
        return result
    }
}
