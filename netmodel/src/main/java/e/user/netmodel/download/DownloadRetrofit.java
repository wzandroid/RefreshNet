package e.user.netmodel.download;

import e.user.netmodel.interceptor.Transformer;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DownloadRetrofit {
    private static final String DOWNLOAD_BASE = "";

    private Retrofit mRetrofit;

    private DownloadRetrofit(){
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(DOWNLOAD_BASE)
                .build();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        return DownloadRetrofit
                .getInstance()
                .getRetrofit()
                .create(DownloadApi.class)
                .downloadFile(fileUrl)
                .compose(Transformer.<ResponseBody>switchSchedulers());
    }

    public static DownloadRetrofit getInstance(){
        return SingleHolder.INSTANCE;
    }

    private static class SingleHolder{
        private static final DownloadRetrofit INSTANCE = new DownloadRetrofit();
    }
}
