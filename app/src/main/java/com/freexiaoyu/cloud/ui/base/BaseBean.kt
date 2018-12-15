package com.freexiaoyu.cloud.ui.base

/**
 * Created by DIY on 2018-11-19. 18:41
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */
class BaseBean<T> {
    private var code: Int = 0
    private var msg: String? = null
    private var status: Int = 0
    private var result: T? = null

    fun getCode(): Int {
        return code
    }

    fun setCode(code: Int) {
        this.code = code
    }

    fun getMsg(): String? {
        return msg
    }

    fun setMsg(msg: String) {
        this.msg = msg
    }

    fun getStatus(): Int {
        return status
    }

    fun setStatus(status: Int) {
        this.status = status
    }

    fun getResult(): T? {
        return result
    }

    fun setResult(result: T) {
        this.result = result
    }
}