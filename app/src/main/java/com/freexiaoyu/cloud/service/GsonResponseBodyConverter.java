package com.freexiaoyu.cloud.service;

import com.freexiaoyu.cloud.bean.BaseResultBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by DIY on 2018-07-06. 11:39
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody,
        T> {
    private final Gson gson;
    private final Type type;
    private final static int SUCCESS_CODE=0;//接口返回功能标识
    private final static int FAILURE_CODE=-1;//接口返回失败标识 -1

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    /**
     * 针对数据返回成功、错误不同类型字段处理
     */
    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            BaseResultBean<T> result = gson.fromJson(response, BaseResultBean.class);
            int status = result.getStatus();
            if (status == SUCCESS_CODE) {
                return gson.fromJson(response, type);
            } else {
                BaseResultBean<T> errResponse = gson.fromJson(response, BaseResultBean.class);
                if (status == FAILURE_CODE) {
                    throw new ResultException(errResponse.getMessage(), status);
                } else {
                    throw new ResultException(errResponse.getMessage(), status);
                }
            }
        } finally {
            value.close();
        }
    }
}
