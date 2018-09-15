package com.wz.refreshnet.http;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

/**
 * Created by Administrator on 2018/3/15 0015.
 * 统一处理错误信息
 */

public abstract class ApiCallBack implements HttpOnNextListener {

    public abstract void onSuccess(String result,String method);
    public void onError(String method){
    }

    @Override
    public void onNext(String resulte, boolean isCache, String method) {
        //可以根据公司的需求进行统一的请求成功的逻辑处理

    }

    @Override
    public void onError(ApiException e, String method) {
        onError(method);
    }
}
