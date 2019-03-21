package e.user.netmodel.interceptor;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import e.user.netmodel.RxHttpUtils;
import e.user.netmodel.interfaces.SPKeys;
import e.user.netmodel.utils.AppUtils;
import e.user.netmodel.utils.SPUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求拦截器  统一添加请求头使用
 */
public class HeaderInterceptor implements Interceptor {

    private Map<String, Object> headerMaps = new TreeMap<>();

    public HeaderInterceptor(Map<String, Object> headerMaps) {
        this.headerMaps = headerMaps;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder request = chain.request().newBuilder();
        if (headerMaps != null && headerMaps.size() > 0) {
            for (Map.Entry<String, Object> entry : headerMaps.entrySet()) {
                request.addHeader(entry.getKey(), (String) entry.getValue());
            }
        } else {
            request
                    .addHeader("From-Client", "Android")
                    .addHeader("Version", getAppVersion())
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .addHeader("token", getToken())
                    .addHeader("userAgent", "android/" + getSystemModel() + "/" + AppUtils.getSystemVersion() + "/" + getAppVersion());
//                    .addHeader("imei", getIMEI())
//                    .addHeader("aid", getUUID())
//                    .addHeader("User-Agent", "golf_" + getAppVersion() + "_Android_" + AppUtils.getSystemVersion())
//                    .addHeader("Content-Type", "application/json");

        }
        return chain.proceed(request.build());
    }


    private String getIMEI() {
        return AppUtils.getIMEI();
    }

    private String getUUID() {
        return AppUtils.getUUID();
    }

    private String getAppVersion() {
        return AppUtils.getAppVersion();
    }

    private String getToken() {
        return (String) SPUtils.get(RxHttpUtils.getContext(), SPKeys.TOKEN, "");
    }

    private String getUserId() {
        return (String) SPUtils.get(RxHttpUtils.getContext(), SPKeys.USERID, "");
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }
}
