package com.freexiaoyu.cloud.utils

import java.util.ArrayList

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView

/**
 * Created by DIY on 2017/5/3. 18:00
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:自定义字体
 */

object FontCustom {
    //字体库路径
    internal var fongUrl = ""
    internal var tf: Typeface? = null

    /***
     * 设置字体
     *
     * @return
     */
    fun setFont(context: Context): Typeface? {
        if (tf == null) {
            tf = Typeface.createFromAsset(context.assets, fongUrl)
        }
        return tf
    }

    fun changeFonts(root: ViewGroup, context: Context) {
        val tf = setFont(context)
        for (i in 0 until root.childCount) {
            val v = root.getChildAt(i)
            if (v is TextView) {
                v.typeface = tf
            } else if (v is Button) {
                v.typeface = tf
            } else if (v is EditText) {
                v.typeface = tf
            } else if (v is RadioButton) {
                v.typeface = tf
            } else if (v is ViewGroup) {
                changeFonts(v, context)
            }
        }

    }


    /***
     * 获取该activity所有view
     * @param context
     * @return
     */
    fun getAllChildViews(context: Context): List<View> {
        val view = (context as Activity).window.decorView
        return getAllChildViews(view)
    }

    private fun getAllChildViews(view: View): List<View> {
        val allchildren = ArrayList<View>()
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val viewchild = view.getChildAt(i)
                allchildren.add(viewchild)
                allchildren.addAll(getAllChildViews(viewchild))
            }
        }
        return allchildren
    }


    fun changeFonts(view: List<View>, context: Context) {
        val tf = setFont(context)
        for (i in view.indices) {
            val v = view[i]
            if (v is TextView) {
                v.typeface = tf
            } else if (v is Button) {
                v.typeface = tf
            } else if (v is EditText) {
                v.typeface = tf
            } else if (v is RadioButton) {
                v.typeface = tf
            }
        }

    }


    /***
     * 修改单个控件字体样式
     * @param v 当前控件
     * @param context 上下文
     */
    fun changeFonts(v: View, context: Context) {
        val tf = setFont(context)
        if (v is TextView) {
            v.typeface = tf
        } else if (v is Button) {
            v.typeface = tf
        } else if (v is EditText) {
            v.typeface = tf
        } else if (v is RadioButton) {
            v.typeface = tf
        }
    }

}
