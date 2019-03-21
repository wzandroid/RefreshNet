package e.user.netmodel.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * API初始化类
 */
public class NetWorkManager {
    private static Retrofit retrofit;
    private static volatile Request request = null;
    private static final int DEFAULT_TIME_OUT = 10;
    private static final int DEFAULT_READ_TIME_OUT = 10;

    /**
     * 初始化必要对象和参数
     */
    private NetWorkManager() {
        // 初始化okhttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//读操作超时时间

        // 初始化Retrofit
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BaseUrl.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create()) //返回String 方便统一处理
                .build();
    }

    public static NetWorkManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder{
        private static final NetWorkManager INSTANCE = new NetWorkManager();
    }

    public ServiceInterface getService(){
        return retrofit.create(ServiceInterface.class);
    }
}
