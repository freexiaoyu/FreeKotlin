package com.freexiaoyu.cloud.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.freexiaoyu.cloud.Interface.IimageListener
import com.freexiaoyu.cloud.R

/**
 * Created by DIY on 2017/5/3. 17:41
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

class FreeImageLoader : IimageListener {
    protected var defaultDrawable = R.mipmap.ic_launcher//默认显示图片

    override fun display(imageView: ImageView, url: String, progressId: Int, errorId: Int, tag: Any) {
        showImage(url, imageView, errorId, errorId)
    }

    override fun display(imageView: ImageView, url: String, progressId: Int, errorId: Int) {
        showImage(url, imageView, errorId, errorId)
    }

    override fun display(imageView: ImageView, url: String, progressId: Int) {
        showImage(url, imageView, defaultDrawable, defaultDrawable)
    }

    override fun display(imageView: ImageView, url: String) {
        showImage(url, imageView, defaultDrawable, defaultDrawable)
    }

    override fun display(imageView: ImageView, uri: Uri) {
        Glide.with(imageView.context)
                .load(uri)
                .into(imageView)
    }

    fun showImage(url: String, imageView: ImageView, defImgId: Int, errorImgId: Int) {
        val requestOptions = setRequestOptions(defImgId, errorImgId)
        Glide.with(imageView.context).load(url).apply(requestOptions).into(imageView)
    }

    override fun displayCircle(imageView: ImageView, url: String) {
        val requestOptions = setRequestOptions(defaultDrawable, defaultDrawable)
        requestOptions.transform(CircleTransform())
        Glide.with(imageView.context).load(url).apply(requestOptions).into(imageView)
    }

    override fun displayCircle(imageView: ImageView, url: String, defImgId: Int) {
        val requestOptions = setRequestOptions(defImgId, defImgId)
        requestOptions.transform(CircleTransform())
        Glide.with(imageView.context).load(url).apply(requestOptions).into(imageView)
    }


    fun setRequestOptions(defImgId: Int, errorImgId: Int): RequestOptions {
        return RequestOptions()
                .placeholder(defImgId)
                .error(errorImgId)
    }

    companion object {
        private var instance: FreeImageLoader? = null
            get() {
                if (field == null) {
                    field = FreeImageLoader()
                }
                return field
            }

        @Synchronized
        fun get(): FreeImageLoader {
            return instance!!
        }
    }

}
