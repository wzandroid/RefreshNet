package com.wz.refreshnet.global;

import com.wz.refreshnet.http.ApiUrl;

/**
 * Created by User on 2018/5/30.
 */

public class GlobalConstant {
    private static final String HOST = ApiUrl.BASE_URL;
    private static final String TEST_HOST = "";
    private static boolean IS_TEST = false;

    public static String getHost(){
        return IS_TEST?TEST_HOST:HOST;
    }
}
