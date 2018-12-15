package com.freexiaoyu.cloud.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by DIY on 2018-11-20. 18:48
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */
class LogUtil {
    val DEBUG = true

    fun d(TAG: String, method: String, msg: String) {
        Log.d(TAG, "[$method]$msg")
    }

    fun d(TAG: String, msg: String) {
        if (DEBUG) {
            Log.d(TAG, "[" + getFileLineMethod() + "]" + msg)
        }
    }

    fun d(msg: String) {
        if (DEBUG) {
            Log.d(_FILE_(), "[" + getLineMethod() + "]" + msg)
        }
    }

    fun e(msg: String) {
        if (DEBUG) {
            Log.e(_FILE_(), getLineMethod() + msg)
        }
    }

    fun e(TAG: String, msg: String) {
        if (DEBUG) {
            Log.e(TAG, getLineMethod() + msg)
        }
    }

    fun getFileLineMethod(): String {
        val traceElement = Exception().stackTrace[2]
        val toStringBuffer = StringBuffer("[")
                .append(traceElement.fileName).append(" | ")
                .append(traceElement.lineNumber).append(" | ")
                .append(traceElement.methodName).append("]")
        return toStringBuffer.toString()
    }

    fun getLineMethod(): String {
        val traceElement = Exception().stackTrace[2]
        val toStringBuffer = StringBuffer("[")
                .append(traceElement.lineNumber).append(" | ")
                .append(traceElement.methodName).append("]")
        return toStringBuffer.toString()
    }

    fun _FILE_(): String {
        val traceElement = Exception().stackTrace[2]
        return traceElement.fileName
    }

    fun _FUNC_(): String {
        val traceElement = Exception().stackTrace[1]
        return traceElement.methodName
    }

    fun _LINE_(): Int {
        val traceElement = Exception().stackTrace[1]
        return traceElement.lineNumber
    }

    fun _TIME_(): String {
        val now = Date()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        return sdf.format(now)
    }
}