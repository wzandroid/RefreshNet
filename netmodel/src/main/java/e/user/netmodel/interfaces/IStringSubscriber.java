package e.user.netmodel.interfaces;

import io.reactivex.disposables.Disposable;

//自定义接口返回类型
public interface IStringSubscriber {
    /**
     * doOnSubscribe 回调
     *
     * @param d Disposable
     */
    void doOnSubscribe(Disposable d);

    /**
     * 错误回调
     *
     * @param errorMsg 错误信息
     */
    void doOnError(String errorMsg);

    /**
     * 成功回调
     *
     * @param string data
     */
    void doOnNext(String string);

    /**
     * 请求完成回调
     */
    void doOnCompleted();
}
