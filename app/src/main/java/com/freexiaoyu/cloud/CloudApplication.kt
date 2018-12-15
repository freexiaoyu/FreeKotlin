package com.freexiaoyu.cloud

import android.app.Application
import android.content.Context

/**
 * Created by DIY on 2018-11-19. 19:02
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */
class CloudApplication: Application() {
    companion object {
        private var instance: Application? = null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}