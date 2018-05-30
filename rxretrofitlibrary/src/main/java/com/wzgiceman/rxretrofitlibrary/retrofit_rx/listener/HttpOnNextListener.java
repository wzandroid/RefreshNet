package com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

/**
 * 成功回调处理
 * Created by WZG on 2016/7/16.
 */
public interface HttpOnNextListener {
    /**
     * 成功后回调方法
     *
     * @param result 返回的结果
     * @param isCache 是否是缓存内容
     * @param method 对应的接口
     */
    void onNext(String result, boolean isCache, String method);

    /**
     * 失败
     * 失败或者错误方法
     * 自定义异常处理
     *
     * @param e
     * @param method
     */
    void onError(ApiException e, String method);
}
