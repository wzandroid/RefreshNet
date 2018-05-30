package com.wz.refreshnet.http;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2018/3/15 0015.
 * 定义接口类型和返回值
 */

public interface ApiService {

    @POST(ApiUrl.UPLOAD_IMAGE)
    Observable<String> uploadImage(@QueryMap Map<String, Object> map,MultipartBody.Part part);
}
