package e.user.netmodel.observer.base;

import e.user.netmodel.exception.ApiException;
import e.user.netmodel.interfaces.IStringSubscriber;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseStringObserver implements Observer<String>, IStringSubscriber {

    @Override
    public void onSubscribe(Disposable d) {
        doOnSubscribe(d);
    }

    @Override
    public void onNext(String string) {
        doOnNext(string);
    }

    @Override
    public void onError(Throwable e) {
        String error = ApiException.handleException(e).getMessage();
        doOnError(error);
    }

    @Override
    public void onComplete() {
        doOnCompleted();
    }

}
