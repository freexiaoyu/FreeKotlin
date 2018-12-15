package com.freexiaoyu.cloud.Interface

import android.content.Context
import android.net.Uri
import android.widget.ImageView

/**
 * Created by DIY on 2018-11-20. 09:48
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */
interface IimageListener {
     fun display(imageView: ImageView,
                 url: String, progressId: Int, errorId: Int,
                 tag: Any)

     fun display(imageView: ImageView,
                 url: String, progressId: Int, errorId: Int)

     fun display(imageView: ImageView,
                 url: String, progressId: Int)

     fun display(imageView: ImageView,
                 url: String)

     fun display(imageView: ImageView, uri: Uri)
     fun displayCircle(imageView: ImageView, url: String)
     fun displayCircle(imageView: ImageView, url: String, defImgId: Int)
}