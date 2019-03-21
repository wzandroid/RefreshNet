package e.user.netmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import e.user.netmodel.net.NetWorkManager;
import e.user.netmodel.net.StringCallBack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getDataForNet();
        findViewById(R.id.btn).setOnClickListener(this);
    }

    private void getDataForNet(){
        NetWorkManager.getInstance().getService().getTop250(1,20).enqueue(new StringCallBack() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Log.d("TAG","网络请求的返回结果为："+response);
            }

            @Override
            public void onFailed(Exception e) {
                Log.d("TAG","返回错误结果为："+e.getMessage());
            }
        });
    }

    private void getDataForNetByRxjava(){

    }

    @Override
    public void onClick(View v) {
        getDataForNet();
    }
}
