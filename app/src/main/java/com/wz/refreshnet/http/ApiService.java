package com.wz.refreshnet.http;

import com.wz.refreshnet.bean.BookBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 * Created by Administrator on 2018/3/15 0015.
 * 定义接口类型和返回值
 */

public interface ApiService {

    @POST(ApiUrl.UPLOAD_IMAGE)
    Observable<String> uploadImage(@QueryMap Map<String, Object> map, MultipartBody.Part part);

    @GET(ApiUrl.GET_BOOK)
    Observable<BookBean> getBookById(@Path("id") int id);

    //获取豆瓣Top250 榜单
    @GET(ApiUrl.TOP_250)
    Observable<String> getTop250(@Query("start") int start, @Query("count")int count);
}
