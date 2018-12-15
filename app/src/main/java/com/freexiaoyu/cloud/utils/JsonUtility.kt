package com.freexiaoyu.cloud.utils

import android.util.Log
import com.freexiaoyu.cloud.utils.JsonUtility.getElement
import com.freexiaoyu.cloud.utils.JsonUtility.parse

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

import java.util.ArrayList


object JsonUtility {

    /**
     * 将json转化为对象。
     *
     * @param json
     * Json字符串
     * @param classOfT
     * 对象class
     * @return 对象
     */
    fun <T> fromJson(json: String, classOfT: Class<T>): T? {
        try {
            return Gson().fromJson(json, classOfT)
        } catch (e: JsonSyntaxException) {
            Log.e("JsonUtility", "fromJson is null!")
            return null
        }

    }

    /**
     * 将json转化为对象。
     *
     * @param element
     * json元素
     * @param typeToken
     * 类型
     * @return 对象
     */
    fun <T> fromJson(element: JsonElement?, typeToken: TypeToken<T>): T? {

        var t: T? = null
        try {
            t = Gson().fromJson<T>(element, typeToken.type)
        } catch (e: JsonSyntaxException) {
            Log.e("JsonUtility", "fromJson<T> is null!")
        }

        return t
    }

    /**
     * 将字符串解析为Json对象。
     *
     * @param json
     * Json字符串
     * @return 对象
     */
    fun parse(json: String): JsonObject? {
        var `object`: JsonObject? = null
        try {
            `object` = JsonParser().parse(json).asJsonObject
        } catch (e: JsonSyntaxException) {
            Log.e("JsonUtility", "parse is null!")
        }

        return `object`
    }

    /**
     * 取得Json元素。
     *
     * @param json
     * json字符串
     * @param key
     * 元素key
     * @return Json元素
     */
    fun getElement(json: String, key: String): JsonElement? {

        try {
            return getElement(parse(json), key)
        } catch (e: Exception) {
            Log.e("JsonUtility", "getElement is null!")
            return null
        }

    }

    /**
     * 取得Json元素。
     *
     * @param object
     * json对象
     * @param key
     * 元素key
     * @return Json元素
     */
    fun getElement(`object`: JsonObject?, key: String): JsonElement? {

        var element: JsonElement? = null
        try {
            element = parseElement(`object`!!.get(key))
        } catch (e: Exception) {
            Log.e("JsonUtility", "getElement is null!")
        }

        return element
    }

    /**
     * Json元素转换。
     *
     * @param element
     * 元素
     * @return 转换后Json元素
     */
    private fun parseElement(element: JsonElement): JsonElement? {

        return if (element is JsonNull) {
            null
        } else element

    }

    /**
     * 取得字符串。
     *
     * @param element
     * json元素
     * @return 字符串
     */
    fun getAsString(element: JsonElement?): String? {
        if (element != null) {
            var str: String? = null
            try {
                str = element.asString
            } catch (e: Exception) {
                Log.e("JsonUtility", "getAsString is null!")
            }

            return str
        } else {
            return null
        }
    }

    /**
     * 取得整数。
     *
     * @param element
     * json元素
     * @return 整数
     */
    fun getAsInt(element: JsonElement?): Int {
        if (element != null) {
            var i = -9999
            try {
                i = element.asInt
            } catch (e: Exception) {
                Log.e("JsonUtility", "getAsInt is null!")
            }

            return i
        } else {
            return -999
        }
    }

    /**
     * 取得数值。
     *
     * @param element
     * json元素
     * @return 数值
     */
    fun getAsDouble(element: JsonElement?): Double {
        if (element != null) {
            var d = -999.9
            try {
                d = element.asDouble
            } catch (e: Exception) {
                Log.e("JsonUtility", "getAsDouble is null!")
            }

            return d
        } else {
            return -999.9
        }
    }

    /**
     * 取得布尔值。
     *
     * @param element
     * json元素
     * @return 布尔值
     */
    fun getAsBoolean(element: JsonElement?): Boolean {
        if (element != null) {
            var b = false
            try {
                b = element.asBoolean
            } catch (e: Exception) {
                Log.e("JsonUtility", "getAsBoolean is null!")
            }

            return b
        } else {
            return false
        }
    }

    /**
     * 将json转化为对象。
     *
     * @param element
     * json元素
     * @param classOfT
     * 对象class
     * @return 对象
     */
    fun <T> fromJson(element: JsonElement, classOfT: Class<T>): T? {

        try {
            return Gson().fromJson(element, classOfT)
        } catch (e: JsonSyntaxException) {
            Log.e("JsonUtility", "fromJson<T> is null!")
            return null
        }

    }

    /**
     * 将json转化为对象。
     *
     * @param json
     * json字符串
     * @param typeToken
     * 类型
     * @return 对象
     */
    fun <T> fromJson(json: String, typeToken: TypeToken<T>): T? {

        var t: T? = null
        try {
            t = Gson().fromJson<T>(json, typeToken.type)
        } catch (e: JsonSyntaxException) {
            Log.e("JsonUtility", "fromJson<T> is null!")
        }

        return t
    }


    /***
     * 将json转为对象
     *
     * @param json
     * 结果集
     * @param key
     * 名称
     * @param t
     * 类型
     * @return 对象
     */
    fun <T> fromBean(json: String?, key: String, t: Class<T>): T? {
        return if (json != null) {
            try {
                fromJson(getElement(parse(json), key),
                        object : TypeToken<T>() {

                        })
            } catch (e: Exception) {
                Log.e("JsonUtility", "fromJson<T> is null!")
                null
            }

        } else {
            null
        }
    }

    /***
     * 将json转为List对象
     *
     * @param <T>
     *
     * @param json
     * 结果集
     * @param key
     * 名称
     * @param t
     * 类型
     * @return 对象
    </T> */
    fun <T> fromList(json: String?, key: String, t: Class<T>): ArrayList<T>? {
        return if (json != null) {
            try {
                fromJson(getElement(parse(json), key),
                        object : TypeToken<ArrayList<T>>() {

                        })
            } catch (e: Exception) {
                Log.e("JsonUtility", "fromList<T> is null!")
                null
            }

        } else {
            null
        }
    }


    /***
     * 将从json转为中一个对象
     *
     * @param <T>
     *
     * @param json
     * 结果集
     * @param key
     * 名称
     * @param t
     * 类型
     * @return
     * @return 对象
    </T> */
    fun <T> fromListToObject(json: String?, key: String, t: Class<T>): T? {
        return if (json != null) {
            try {
                //				return fromJson(getElement(parse(json), key),
                //						new TypeToken<T>() {
                //						});

                Gson().fromJson(getElement(parse(json), key),
                        t)

            } catch (e: Exception) {
                Log.e("JsonUtility", "fromList<T> is null!")
                null
            }

        } else {
            null
        }
    }

    fun toJson(src: Any): String {
        return Gson().toJson(src)
    }

}
/**
 * 私有构造函数
 */// 禁止实例化
