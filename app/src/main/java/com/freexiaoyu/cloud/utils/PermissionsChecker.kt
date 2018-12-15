package com.freexiaoyu.cloud.utils

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat

/**
 * Created by DIY on 2017/5/4. 16:21
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe: 检查权限的工具类
 */

class PermissionsChecker(context: Context) {
    private val mContext: Context

    init {
        mContext = context.applicationContext
    }

    // 判断权限集合
    fun lacksPermissions(vararg permissions: String): Boolean {
        for (permission in permissions) {
            if (lacksPermission(permission)) {
                return true
            }
        }
        return false
    }

    // 判断是否缺少权限
    private fun lacksPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_DENIED
    }
}
