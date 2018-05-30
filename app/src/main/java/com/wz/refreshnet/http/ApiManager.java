package com.wz.refreshnet.http;

import android.app.ProgressDialog;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.wz.refreshnet.Global.GlobalConstant;
import com.wz.refreshnet.Utils.Arith;
import com.wz.refreshnet.Utils.LogUtil;
import com.wz.refreshnet.dialog.ProcessDialog;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.HttpManagerApi;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.upload.ProgressRequestBody;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.upload.UploadProgressListener;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2018/3/15 0015.
 * API 管理类
 */

public class ApiManager extends HttpManagerApi {
    private ProgressDialog loadingDialog = null;

    @Override
    public ProgressDialog getDialog() {
        return loadingDialog;
    }

    public ApiManager(HttpOnNextListener onNextListener, RxAppCompatActivity appCompatActivity) {
        super(onNextListener, appCompatActivity);
        setBaseUrl(GlobalConstant.getHost());
        loadingDialog = new ProcessDialog(appCompatActivity);
        setCache(true);
        setShowProgress(false);
    }

    public void uploadImage(Map<String,Object> map,File file) {
        setMethod(ApiUrl.UPLOAD_IMAGE);
        setCache(false);
        setShowProgress(true);
        LogUtil.d("上传图片的url="+getMethod());
        doHttpDeal(getRetrofit().create(ApiService.class).uploadImage(map,getPart(file)));
    }

    private MultipartBody.Part getPart(File file){
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("ImageFile",file.getName(),
                new ProgressRequestBody(requestBody,progressListener));
        return part;
    }

    private UploadProgressListener progressListener = new UploadProgressListener() {
        @Override
        public void onProgress(final long currentBytesCount, final long totalBytesCount) {
            /*回到主线程中，可通过timer等延迟或者循环避免快速刷新数据*/
            Observable.just(currentBytesCount).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {

                @Override
                public void call(Long aLong) {
                    if(getDialog() instanceof ProcessDialog){
                        String loadMsg = "已上传"+(Arith.div(aLong*100,totalBytesCount,2)+"%");
                        ((ProcessDialog)getDialog()).setLoadText(loadMsg);
                    }
                }
            });
        }
    };
}
