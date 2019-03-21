package e.user.netmodel.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.UUID;

import e.user.netmodel.RxHttpUtils;

public class AppUtils {

    /**
     * 获取手机版本号
     *
     * @return 返回版本号
     */
    public static String getAppVersion() {
        PackageInfo pi;
        String versionNum;
        try {
            PackageManager pm = RxHttpUtils.getContext().getPackageManager();
            pi = pm.getPackageInfo(RxHttpUtils.getContext().getPackageName(), PackageManager.GET_CONFIGURATIONS);
            versionNum = pi.versionName;
        } catch (Exception e) {
            versionNum = "0";
        }
        return versionNum;
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }


    /**
     * 获取手机唯一标识码UUID
     *
     * @return 返回UUID
     * <p>
     * 记得添加相应权限
     * android.permission.READ_PHONE_STATE
     */
    public static String getUUID() {

        Context context = RxHttpUtils.getContext();

        String uuid = (String) SPUtils.get(context, "PHONE_UUID", "");

        if (TextUtils.isEmpty(uuid)) {

            try {
                TelephonyManager telephonyManager = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                @SuppressLint({"MissingPermission", "HardwareIds"}) String tmDevice = telephonyManager.getDeviceId();
                @SuppressLint({"MissingPermission", "HardwareIds"}) String tmSerial = telephonyManager.getSimSerialNumber();

                @SuppressLint("HardwareIds") String androidId = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
                UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
                String uniqueId = deviceUuid.toString();
                uuid = uniqueId;
                SPUtils.put(context, "PHONE_UUID", uuid);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return uuid;

    }


    /**
     * 获取手机IMEI
     *
     * @return 返回IMEI
     * <p>
     * 记得添加相应权限
     * android.permission.READ_PHONE_STATE
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getIMEI() {

        Context context = RxHttpUtils.getContext();

        String imei = (String) SPUtils.get(context, "PHONE_IMEI", "");

        if (TextUtils.isEmpty(imei)) {

            try {
                TelephonyManager telephonyManager = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                imei = telephonyManager != null ? telephonyManager.getDeviceId() : "";
                SPUtils.put(context, "PHONE_IMEI", imei);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return imei;

    }
}
