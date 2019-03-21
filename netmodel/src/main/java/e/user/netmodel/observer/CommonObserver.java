package e.user.netmodel.observer;

import android.app.Dialog;

import e.user.netmodel.RxHttpUtils;
import e.user.netmodel.bean.BaseBean;
import e.user.netmodel.observer.base.BaseObserver;
import io.reactivex.disposables.Disposable;

public abstract class CommonObserver<T extends BaseBean> extends BaseObserver<T> {


    private Dialog mProgressDialog;

    public CommonObserver() {
    }

    public CommonObserver(Dialog progressDialog) {
        mProgressDialog = progressDialog;
    }

    public Dialog getLoadingDialog() {
        return mProgressDialog;
    }

    /**
     * 失败回调
     *
     * @param errorMsg
     */
    protected abstract void onError(String errorMsg);

    /**
     * 成功回调
     *
     * @param t
     */
    protected abstract void onSuccess(T t);

    /**
     * 是否隐藏toast
     *
     * @return
     */
    public boolean isHideToast() {
        return false;
    }

    @Override
    public void doOnSubscribe(Disposable d) {
        RxHttpUtils.addDisposable(d);
    }

    @Override
    public void doOnError(String errorMsg) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (!isHideToast()) {
//            ToastUtils.showToast(errorMsg);
        }
        onError(errorMsg);
    }

    @Override
    public void doOnNext(T t) {

        //可以根据需求对code统一处理
        switch (t.getCode()) {
            case 0:
                onSuccess(t);
                break;
            case 201:
                doOnError(t.getMessage());
                break;
            case 401:
                doOnError(t.getMessage());
                break;
            case 404:
                doOnError(t.getMessage());
                break;
            default:
                doOnError(t.getMessage());
        }
    }

    @Override
    public void doOnCompleted() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
