package com.freexiaoyu.cloud.utils

import android.content.Context
import android.widget.Toast


object ToastUtil {
    fun show(context: Context, info: String) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
    }

    fun show(context: Context, info: Int) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
    }

    fun show(context: Context, info: String, duraction: Int) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
    }

    fun show(context: Context, info: Int, duraction: Int) {
        Toast.makeText(context, info, duraction).show()
    }
}
