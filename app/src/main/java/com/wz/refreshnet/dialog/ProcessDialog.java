package com.wz.refreshnet.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.wz.refreshnet.R;


/**
 * Created by User on 2018/5/30.
 */

public class ProcessDialog extends ProgressDialog {
    private TextView textView;

    public ProcessDialog(Context context) {
        super(context,R.style.progress_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);
        textView = (TextView) findViewById(R.id.loading_tv);
    }

    public void setLoadText(String msg){
        textView.setText(msg);
    }
}