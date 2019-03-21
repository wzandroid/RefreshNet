package e.user.netmodel.http;

import okhttp3.OkHttpClient;

public class HttpClient {

    private OkHttpClient.Builder builder;

    private HttpClient(){
        builder = new OkHttpClient.Builder();
    }

    public static HttpClient getInstance(){
        return SingleHolder.INSTANCE;
    }

    public OkHttpClient.Builder getBuilder() {
        return builder;
    }

    private static class SingleHolder{
        private static final HttpClient INSTANCE = new HttpClient();
    }
}
