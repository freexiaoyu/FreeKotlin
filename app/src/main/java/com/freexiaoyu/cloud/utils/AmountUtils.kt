package com.mumenjoy.mmylibrary.utils

import java.math.BigDecimal

/**
 * Created by DIY on 2018/4/13. 16:43
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

object AmountUtils {

    /**金额为分的格式  */
    val CURRENCY_FEN_REGEX = "\\-?[0-9]+"

    /**
     * 将分为单位的转换为元并返回金额格式的字符串 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun changeF2Y(amount: Long?): String {
        if (!amount!!.toString().matches(CURRENCY_FEN_REGEX.toRegex())) {
            throw Exception("金额格式有误")
        }

        var flag = 0
        var amString = amount.toString()
        if (amString[0] == '-') {
            flag = 1
            amString = amString.substring(1)
        }
        val result = StringBuffer()
        if (amString.length == 1) {
            result.append("0.0").append(amString)
        } else if (amString.length == 2) {
            result.append("0.").append(amString)
        } else {
            val intString = amString.substring(0, amString.length - 2)
            for (i in 1..intString.length) {
                if ((i - 1) % 3 == 0 && i != 1) {
                    result.append(",")
                }
                result.append(intString.substring(intString.length - i, intString.length - i + 1))
            }
            result.reverse().append(".").append(amString.substring(amString.length - 2))
        }
        return if (flag == 1) {
            "-" + result.toString()
        } else {
            result.toString()
        }
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun changeF2Y(amount: String): String {
        if (!amount.matches(CURRENCY_FEN_REGEX.toRegex())) {
            throw Exception("金额格式有误")
        }
        return BigDecimal.valueOf(java.lang.Long.valueOf(amount)!!).divide(BigDecimal(100)).toString()
    }

    /**
     * 将元为单位的转换为分 （乘100）
     *
     * @param amount
     * @return
     */
    fun changeY2F(amount: Long?): String {
        return BigDecimal.valueOf(amount!!).multiply(BigDecimal(100)).toString()
    }

    /**
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
     *
     * @param amount
     * @return
     */
    fun changeY2F(amount: String): String {
        val currency = amount.replace("\\$|\\￥|\\,".toRegex(), "")  //处理包含, ￥ 或者$的金额
        val index = currency.indexOf(".")
        val length = currency.length
        var amLong: Long? = 0L
        if (index == -1) {
            amLong = java.lang.Long.valueOf(currency + "00")
        } else if (length - index >= 3) {
            amLong = java.lang.Long.valueOf(currency.substring(0, index + 3).replace(".", ""))
        } else if (length - index == 2) {
            amLong = java.lang.Long.valueOf(currency.substring(0, index + 2).replace(".", "") + 0)
        } else {
            amLong = java.lang.Long.valueOf(currency.substring(0, index + 1).replace(".", "") + "00")
        }
        return amLong!!.toString()
    }

}
