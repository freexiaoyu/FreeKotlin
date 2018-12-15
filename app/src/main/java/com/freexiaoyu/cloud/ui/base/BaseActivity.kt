package com.freexiaoyu.cloud.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.freexiaoyu.cloud.R
import com.freexiaoyu.cloud.bean.MessageEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by DIY on 2018-11-19. 18:34
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */
abstract class BaseActivity:AppCompatActivity() {
    //lateinit修饰符  变量需要在定义后才赋值的
    lateinit  var mContext: Context
    lateinit var mIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContentView(provideContentViewId())//布局
        mContext = this
        initView()
        initData()
        initListener()
    }

    abstract fun provideContentViewId(): Int //用于引入布局文件
    /***
     * 在初始化布局之前操作
     */
    abstract fun init()

    /***
     * 布局初始化完成后控件初始化
     */
    abstract fun initView()

    /***
     * 在初始化布局之前操作
     */
    abstract fun initData()

    /***
     * 事件
     */
    abstract fun initListener()


    abstract fun messageHandler(event: MessageEvent)

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }


    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        messageHandler(event)
    }


    fun toolBar(isBlack: Boolean?, toolbar: Toolbar, title: String) {
        setSupportActionBar(toolbar)
        if (isBlack!!) {
            toolbar.setNavigationIcon(R.drawable.title_bar_back_btn_white)
            toolbar.setNavigationOnClickListener { finish() }
        }
    }

}
