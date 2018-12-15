package com.freexiaoyu.cloud.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by DIY on 2017/5/3. 17:56
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:正则工具类
 */

object RegUtils {
    /**
     * 邮箱验证
     * @param strEmail 要验证的字符
     * @return
     */
    fun isEmail(strEmail: String): Boolean {
        val strPattern = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"
        val p = Pattern.compile(strPattern)
        val m = p.matcher(strEmail)
        return m.matches()
    }

    /**
     * 验证手机
     * @param mobile 要验证的字符
     * @return
     */
    fun isMobile(mobile: String): Boolean {
        val strPattern = "^1[\\d]{10}$"
        val p = Pattern.compile(strPattern)
        val m = p.matcher(mobile)
        return m.matches()
    }


    fun isNumeric(str: String): Boolean {
        val pattern = Pattern.compile("[0-9]*")
        return pattern.matcher(str).matches()
    }

    fun isUrl(str: String): Boolean {
        val strPattern = "[http|https]+://[^s]*"
        val p = Pattern.compile(strPattern)
        val m = p.matcher(str)
        return m.matches()
    }
}
