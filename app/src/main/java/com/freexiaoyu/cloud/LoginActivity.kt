package com.freexiaoyu.cloud

import com.freexiaoyu.cloud.bean.MessageEvent
import com.freexiaoyu.cloud.ui.base.BaseActivity
import com.freexiaoyu.cloud.utils.Camera
import com.freexiaoyu.cloud.utils.FreeImageLoader
import com.freexiaoyu.cloud.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun provideContentViewId(): Int {
        return R.layout.activity_login
    }

    override fun init() {
    }

    override fun initView() {
        tv_title.text = "我是登录"
        FreeImageLoader.get().display(iv_photo, "")
        Camera.startCamera(mContext, 0)
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    override fun messageHandler(event: MessageEvent) {
    }


}
