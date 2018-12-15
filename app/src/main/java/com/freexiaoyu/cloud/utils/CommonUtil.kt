package com.freexiaoyu.cloud.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Created by DIY on 2017/8/3. 16:02
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

object CommonUtil {
    /**
     * 获取APP版本
     *
     * @param context 环境
     * @return String
     */
    @Throws(PackageManager.NameNotFoundException::class)
    fun getAppVersionCode(context: Context): Int {
        val pm = context.packageManager
        val pi = pm.getPackageInfo(context.packageName, 0)
        return pi.versionCode
    }

    /**
     * 获取APP版本
     *
     * @param context 环境
     * @return String
     */
    @Throws(PackageManager.NameNotFoundException::class)
    fun getAppVersionName(context: Context): String {
        val pm = context.packageManager
        val pi = pm.getPackageInfo(context.packageName, 0)
        return pi.versionName
    }

    /***
     * 获取手机唯一标识
     * @param context
     * @return
     */
    fun getIMSI(context: Context): String {
        var imsi="";
      try {
          val phoneMgr = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
          imsi=phoneMgr.deviceId
      } catch (e: Exception) {
          e.printStackTrace()
      }
        return imsi;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dpToPixel(context: Context, dpValue: Float): Int {
        val scale = getDensity(context)
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun pixelsToDp(context: Context, pxValue: Float): Int {
        val scale = getDensity(context)
        return (pxValue / scale + 0.5f).toInt()
    }

    /***
     * 屏幕宽度（像素）
     * @param context
     * @return
     */
    fun widthPixels(context: Context): Int {
        return getDisplayMetrics(context).widthPixels
    }

    /**
     * 屏幕高度（像素）
     * @param context
     * @return
     */
    fun heightPixels(context: Context): Int {
        return getDisplayMetrics(context).heightPixels
    }

    /**
     * 获得缩放因子 density
     *
     * @param context
     * @return
     */
    fun getDensity(context: Context): Float {
        return getDisplayMetrics(context).density
    }


    fun getDisplayMetrics(context: Context): DisplayMetrics {
        val displaymetrics = DisplayMetrics()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
                .defaultDisplay.getMetrics(displaymetrics)
        return displaymetrics
    }

    /***
     * 检查是否安装某个应用
     * @param mContext
     * @param packagename
     * @return
     */
    fun checkInstall(mContext: Context, packagename: String): Boolean {
        var packageInfo: PackageInfo?
        try {
            packageInfo = mContext.packageManager.getPackageInfo(
                    packagename, 0)

        } catch (e: PackageManager.NameNotFoundException) {
            packageInfo = null
            e.printStackTrace()
        }

        return if (packageInfo == null) {
            false
        } else {
            true
        }
    }
}
