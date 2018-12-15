package com.freexiaoyu.cloud.Interface

/**
 * Created by DIY on 2018-11-20. 09:47
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */
interface ICallBack<T> {
    fun onSuccess(flag: String, key: String, t: T)

    fun onFailure(flag: String, key: String, why: String)
}