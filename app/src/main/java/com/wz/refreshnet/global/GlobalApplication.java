package com.wz.refreshnet.global;

import android.app.Application;

import com.wz.refreshnet.http.ApiUrl;

import e.user.netmodel.RxHttpUtils;

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRxHttpUtils();
    }

    private void initRxHttpUtils(){
        RxHttpUtils.init(this);
        RxHttpUtils.getInstance()
                //开启全局配置
                .config()
                //全局的BaseUrl
                .setBaseUrl(ApiUrl.BASE_URL)
                //全局持久话cookie,保存本地每次都会携带在header中
                .setCookie(false)
                .setHeaders(null)
                //全局ssl证书认证
                //信任所有证书,不安全有风险
                .setSslSocketFactory()
                //使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(getAssets().open("your.cer"))
                //使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(getAssets().open("your.bks"), "123456", getAssets().open("your.cer"))
                //全局超时配置
                .setReadTimeout(15)
                //全局超时配置
                .setWriteTimeout(15)
                //全局超时配置
                .setConnectTimeout(15)
                //全局是否打开请求log日志
                .setLog(true);
    }
}
