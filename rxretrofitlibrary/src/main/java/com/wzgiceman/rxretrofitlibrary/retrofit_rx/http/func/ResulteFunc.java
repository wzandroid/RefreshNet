package com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.func;

import com.wzgiceman.rxretrofitlibrary.R;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.RxRetrofitApp;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.HttpTimeException;

import rx.functions.Func1;

/**
 * 服务器返回数据判断
 * Created by WZG on 2017/3/23.
 */

public class ResulteFunc implements Func1<Object, Object> {
    private static String errorMsg = RxRetrofitApp.getApplication().getString(R.string.data_error);
    @Override
    public Object call(Object o) {
        if (o == null || "".equals(o.toString())) {
            throw new HttpTimeException(HttpTimeException.CHACHE_HTTP_POST_ERROR, errorMsg);
        }
        return o;
    }
}
