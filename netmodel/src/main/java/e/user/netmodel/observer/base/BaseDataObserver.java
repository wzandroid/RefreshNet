package e.user.netmodel.observer.base;

import e.user.netmodel.bean.BaseData;
import e.user.netmodel.exception.ApiException;
import e.user.netmodel.interfaces.IDataSubscriber;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseDataObserver<T> implements Observer<BaseData<T>>, IDataSubscriber<T> {

    @Override
    public void onSubscribe(Disposable d) {
        doOnSubscribe(d);
    }

    @Override
    public void onNext(BaseData<T> baseData) {
        doOnNext(baseData);
    }

    @Override
    public void onError(Throwable e) {
        String error = ApiException.handleException(e).getMessage();
        setError(error);
    }

    @Override
    public void onComplete() {
        doOnCompleted();
    }


    private void setError(String errorMsg) {
        doOnError(errorMsg);
    }

}