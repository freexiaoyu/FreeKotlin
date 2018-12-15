package com.mumenjoy.mmylibrary.utils

import android.graphics.Bitmap

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix

import java.util.Hashtable

/**
 * Created by DIY on 2018/6/20. 16:22
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:条码 二维码生成工具类
 */

object BarcodeUtils {


    /***
     * 生成二维码方法
     * @param str 生成内容
     * @param widthHeight 宽度和高度
     * @return
     * @throws WriterException
     */
    @Throws(WriterException::class)
    fun Create2DCode(str: String, barcodeFormat: BarcodeFormat, widthHeight: Int): Bitmap {
        val hints = Hashtable<EncodeHintType, String>()
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8")
        //生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        val matrix = MultiFormatWriter().encode(str, barcodeFormat, widthHeight, widthHeight)
        val width = matrix.width
        val height = matrix.height
        //二维矩阵转为一维像素数组,也就是一直横着排了
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = -0x1000000
                }
            }
        }

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        //通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

    @Throws(WriterException::class)
    fun creatEAN13(str: String, paramsWidth: Int, paramsHeight: Int): Bitmap {
        val hints = Hashtable<EncodeHintType, String>()
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8")
        //生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        val matrix = MultiFormatWriter().encode(str, BarcodeFormat.EAN_13, paramsWidth, paramsHeight)
        val width = matrix.width
        val height = matrix.height
        //二维矩阵转为一维像素数组,也就是一直横着排了
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = -0x1000000
                }
            }
        }

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        //通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }


    @Throws(WriterException::class)
    fun creatUPCA(str: String, paramsWidth: Int, paramsHeight: Int): Bitmap {
        val hints = Hashtable<EncodeHintType, String>()
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8")
        //生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        val matrix = MultiFormatWriter().encode(str, BarcodeFormat.UPC_A, paramsWidth, paramsHeight)
        val width = matrix.width
        val height = matrix.height
        //二维矩阵转为一维像素数组,也就是一直横着排了
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = -0x1000000
                }
            }
        }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        //通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }


}
