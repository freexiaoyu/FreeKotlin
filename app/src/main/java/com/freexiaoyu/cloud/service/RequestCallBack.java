package com.freexiaoyu.cloud.service;

import android.app.ProgressDialog;
import android.content.Context;


import com.freexiaoyu.cloud.Interface.ICallBack;
import com.freexiaoyu.cloud.bean.BaseResultBean;
import com.freexiaoyu.cloud.utils.NetUtil;
import com.freexiaoyu.cloud.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by DIY on 2017/3/31. 16:25
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class RequestCallBack<T> {
    private final static int RESULT_SUCESS_CODE=200;
    private BaseResultBean<T> baseResultBean;
    private ProgressDialog pd;

    public void RXReqeust(Context mContext, Observable<BaseResultBean<T>> mObservable, final ICallBack<T> callBack, Boolean boolShow, String... title) {
        if (!NetUtil.INSTANCE.isNetworkAvailable(mContext)) {
            callBack.onFailure("", "404", "请检查网络!");
            return;
        }
        if (boolShow) {
            showDialog(mContext, title);
        }
        mObservable.subscribeOn(Schedulers.io())//请求数据的事件发生在io线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<BaseResultBean<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResultBean<T> t) {
                        baseResultBean = t;
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (pd != null) {
                            pd.dismiss();
                        }
                        if(e instanceof ResultException){
                            callBack.onFailure("", e.getMessage(), ((ResultException) e).getErrMsg());
                        }else {
                            //数据错误
                            callBack.onFailure("", e.getMessage(), "数据错误");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (pd != null) {
                            pd.dismiss();
                        }
                        if (baseResultBean != null) {
                            if (baseResultBean.getStatus()== RESULT_SUCESS_CODE) {
                                //数据正确，把数据返回/
                                callBack.onSuccess(baseResultBean.getStatus()+""
                                        , baseResultBean.getMessage(), baseResultBean.getData());
                            } else {
                                callBack.onFailure(baseResultBean.getStatus()+""
                                        ,  baseResultBean.getMessage(), baseResultBean.getMessage());
                            }
                        } else {
                            //数据错误
                            callBack.onFailure("", "", "数据错误");
                        }

                    }
                });
    }



    private void showDialog(Context mContext, String... title) {
        if (pd == null) {
            pd = new ProgressDialog(mContext);
        }
        if (title.length == 0) {
            pd.setMessage("正在加载请稍后...");
        } else {
            pd.setMessage(title[0] + "请稍后...");
        }
        if (!pd.isShowing()) {
            pd.show();
        }
    }

}
