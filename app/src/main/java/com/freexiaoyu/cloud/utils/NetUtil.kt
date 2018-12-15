package com.freexiaoyu.cloud.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.Enumeration

object NetUtil {


    /**
     * 通过GPRS获取本地IP
     * @return
     */
    val localIpAddress: String
        get() {
            var ip = "127.0.0.1"
            try {
                val en = NetworkInterface.getNetworkInterfaces()
                while (en.hasMoreElements()) {
                    val intf = en.nextElement()
                    val enumIpAddr = intf.inetAddresses
                    while (enumIpAddr.hasMoreElements()) {
                        val inetAddress = enumIpAddr.nextElement()
                        if (!inetAddress.isLoopbackAddress) {
                            ip = inetAddress.hostAddress.toString()
                        }
                    }
                }
            } catch (ex: SocketException) {
                ex.printStackTrace()
            }

            return ip

        }

    /**
     * 网络连接检测
     * @param ctx
     * @return
     */
    fun isNetworkAvailable(ctx: Context): Boolean {
        var netstatus = false
        try {
            val cm = ctx
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = cm.activeNetworkInfo
            if (info != null && info.isConnected) {
                netstatus = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            netstatus = false
        }

        return netstatus
    }


    /**
     * 获取当前的网络状态 -1：没有网络     1：WIFI网络    2：wap网络    3：net网络
     * @param context
     * @return
     */
    fun getAPNType(context: Context): Int {
        var netType = -1
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo ?: return netType
        val nType = networkInfo.type
        if (nType == ConnectivityManager.TYPE_MOBILE) {

            if (networkInfo.extraInfo.toLowerCase() == "cmnet") {
                netType = 3
            } else {
                netType = 2
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = 1
        }
        return netType
    }
}
