package e.user.netmodel;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import e.user.netmodel.download.DownloadRetrofit;
import e.user.netmodel.http.GlobalRxHttp;
import e.user.netmodel.http.SingleRxHttp;
import e.user.netmodel.interfaces.SPKeys;
import e.user.netmodel.upload.UploadRetrofit;
import e.user.netmodel.utils.SPUtils;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class RxHttpUtils {
    private static Application context;

    private static List<Disposable> disposables;

    private RxHttpUtils(){
        disposables = new ArrayList<>();
    }

    public static Application getContext(){
        checkInitialize();
        return context;
    }

    /**
     * 必须在全局Application先调用，获取context上下文，否则缓存无法使用
     *
     * @param app Application
     */
    public static void init(Application app) {
        context = app;
    }

    /**
     * 检测是否调用初始化方法
     */
    private static void checkInitialize() {
        if (context == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 RxHttpUtils.init() 初始化！");
        }
    }

    public GlobalRxHttp config() {
        return GlobalRxHttp.getInstance();
    }

    /**
     * 使用全局参数创建请求
     *
     * @param cls Class
     * @param <K> K
     * @return 返回
     */
    public static <K> K createApi(Class<K> cls) {
        return GlobalRxHttp.createGApi(cls);
    }

    /**
     * 下载文件
     *
     * @param fileUrl
     * @return
     */
    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        return DownloadRetrofit.downloadFile(fileUrl);
    }

    /**
     * 上传单张图片
     *
     * @param uploadUrl 地址
     * @param filePath  文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImg(String uploadUrl, String filePath) {
        return UploadRetrofit.uploadImg(uploadUrl, filePath);

    }

    /**
     * 获取Cookie
     *
     * @return HashSet
     */
    public static HashSet<String> getCookie() {
        HashSet<String> preferences = (HashSet<String>) SPUtils.get(SPKeys.TOKEN, new HashSet<String>());
        return preferences;
    }

    /**
     * 获取disposable 在onDestroy方法中取消订阅disposable.dispose()
     *
     * @param disposable disposable
     */
    public static void addDisposable(Disposable disposable) {
        if (disposables != null) {
            disposables.add(disposable);
        }
    }

    /**
     * 取消所有请求
     */
    public static void cancelAllRequest() {
        if (disposables != null) {
            for (Disposable disposable : disposables) {
                disposable.dispose();
            }
            disposables.clear();
        }
    }

    /**
     * 取消单个请求
     */
    public static void cancelSingleRequest(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    /**
     * 获取单个请求配置实例
     *
     * @return SingleRxHttp
     */
    public static RxHttpUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取单个请求配置实例
     *
     * @return SingleRxHttp
     */
    public static SingleRxHttp getSInstance() {
        return SingleRxHttp.getInstance();
    }

    //静态内部类实现单例
    private static class SingletonHolder{
        private static final RxHttpUtils INSTANCE = new RxHttpUtils();
    }
}
