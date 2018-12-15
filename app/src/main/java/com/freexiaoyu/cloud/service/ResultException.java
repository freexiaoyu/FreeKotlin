package com.freexiaoyu.cloud.service;

import java.io.IOException;

/**
 * Created by DIY on 2018-07-06. 11:47
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class ResultException extends IOException {

    private String errMsg;
    private int errCode;

    public ResultException(String errMsg, int errCode){
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
