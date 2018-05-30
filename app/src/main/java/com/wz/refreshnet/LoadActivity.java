package com.wz.refreshnet;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.downlaod.DownInfo;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.utils.DbDwonUtil;

import java.io.File;
import java.util.List;

/**
 * Created by User on 2018/5/30.
 */

public class LoadActivity extends AppCompatActivity {
    private DbDwonUtil dbUtil;
    List<DownInfo> listData;
    private final int REQUEST_EXTERNAL_STORAGE = 1;
    private String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyStoragePermissions(this);
    }

    /*数据*/
    private void initResource() {
        dbUtil = DbDwonUtil.getInstance();
        listData = dbUtil.queryDownAll();
        /*第一次模拟服务器返回数据掺入到数据库中*/
        if (listData.isEmpty()) {
            String[] downUrl = new String[]{"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", "http://clips.vorwaerts-gmbh" +
                    ".de/big_buck_bunny.mp4"};
            for (int i = 0; i < downUrl.length; i++) {
                File outputFile = new File(getExternalFilesDir(null), "tetst" + i + ".mp4");
                DownInfo apkApi = new DownInfo(downUrl[i]);
                apkApi.setId(i);
                apkApi.setUpdateProgress(true);
                apkApi.setSavePath(outputFile.getAbsolutePath());
                dbUtil.save(apkApi);
            }
            listData = dbUtil.queryDownAll();
        }
    }

    /**
     * 申请权限
     * @param activity
     */
    public void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
