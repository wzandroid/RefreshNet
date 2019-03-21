package com.wz.refreshnet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wz.refreshnet.bean.BookBean;
import com.wz.refreshnet.dialog.ProcessDialog;
import com.wz.refreshnet.http.ApiService;

import e.user.netmodel.RxHttpUtils;
import e.user.netmodel.interceptor.Transformer;
import e.user.netmodel.observer.CommonObserver;

public class MainActivity extends AppCompatActivity {
    private ProcessDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProcessDialog(this);
        initUi();
    }

    private void initUi(){
        findViewById(R.id.book_btn).setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.book_btn:
                    getBookForId(2);
                    break;
            }
        }
    };

    private void getBookForId(int id){
        RxHttpUtils.createApi(ApiService.class).getBookById(id)
                .compose(Transformer.<BookBean>switchSchedulers(dialog))
                .subscribe(new CommonObserver<BookBean>() {
                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(BookBean bookBean) {

                    }
                });
    }
}
