package com.freexiaoyu.cloud.ui.base

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freexiaoyu.cloud.bean.MessageEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by DIY on 2018-11-19. 18:42
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */
abstract class BaseFragment:Fragment() {
     var mContext: Context? = null
     var mIntent: Intent? = null
    abstract var rootView: View;
     var isRefresh: Boolean? = true//是否刷新
     var PAGE_INDEX = 1//当前页码只有在列表中用到

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {
        mContext = activity
        init()
        rootView = inflater.inflate(createViewLayoutId(), container, false)
        initView(rootView)
        initData()
        initListener()
        return rootView
    }


    abstract fun createViewLayoutId(): Int
    abstract fun onCreate()
    abstract fun init()
    abstract fun initView(rootView: View)
    abstract fun initData()
    abstract fun initListener()
    abstract fun messageHandler(event: MessageEvent)


    fun isSetRefresh(): Boolean? {
        return true
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }


    override fun onStop() {
        super.onStop()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        messageHandler(event)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }
}