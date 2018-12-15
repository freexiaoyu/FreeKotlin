package com.freexiaoyu.cloud

import android.content.Context
import android.content.Intent
import com.freexiaoyu.cloud.bean.MessageEvent
import com.freexiaoyu.cloud.ui.base.BaseActivity
import com.freexiaoyu.cloud.utils.LogUtil

/**
 * Created by DIY on 2018-11-19. 18:21
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */
import kotlinx.android.synthetic.main.activity_main.*;

class AppActivity : BaseActivity() {
    override fun provideContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun init() {

    }

    override fun initView() {
        tv_title.text = "kotlin"
    }

    override fun initData() {
        var logUtil = LogUtil()
        logUtil.d("", "")
    }

    override fun initListener() {
        btn_submit.setOnClickListener {
            mIntent = Intent(mContext,LoginActivity::class.java)
            startActivity(mIntent)
        }
    }

    override fun messageHandler(event: MessageEvent) {

    }


}