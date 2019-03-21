package e.user.netmodel.upload;

import android.app.Dialog;

import java.io.File;

import e.user.netmodel.interceptor.Transformer;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UploadRetrofit {
    private Retrofit mRetrofit;

    private static String baseUrl = "https://api.github.com/";

    private UploadRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public static Observable<ResponseBody> uploadImg(String uploadUrl, String filePath) {
        File file = new File(filePath);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestFile);

        return UploadRetrofit
                .getInstance()
                .getRetrofit()
                .create(UploadFileApi.class)
                .uploadImg(uploadUrl, body)
                .compose(Transformer.<ResponseBody>switchSchedulers());
    }

    public static Observable<ResponseBody> uploadImg(String uploadUrl, String filePath, Dialog loadingDialog) {
        File file = new File(filePath);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestFile);

        return UploadRetrofit
                .getInstance()
                .getRetrofit()
                .create(UploadFileApi.class)
                .uploadImg(uploadUrl, body);
    }

    public static UploadRetrofit getInstance(){
        return SingleHolder.INSTANCE;
    }

    private static class SingleHolder{
        private static final UploadRetrofit INSTANCE = new UploadRetrofit();
    }
}
