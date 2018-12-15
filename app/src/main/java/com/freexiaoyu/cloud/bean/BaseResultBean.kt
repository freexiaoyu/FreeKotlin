package com.freexiaoyu.cloud.bean

/**
 * Created by DIY on 2018-11-19. 19:00
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */
class BaseResultBean<T> {
    var status: Int = 0
    var message: String? = null
    var data: T? = null
}