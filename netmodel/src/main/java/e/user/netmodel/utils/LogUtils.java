package e.user.netmodel.utils;

import android.util.Log;

public class LogUtils {
    private static final String TAG = "Http";

    public static void d(String msg){
        Log.d(TAG,msg);
    }

    public static void e(String msg){
        Log.e(TAG,msg);
    }
}
