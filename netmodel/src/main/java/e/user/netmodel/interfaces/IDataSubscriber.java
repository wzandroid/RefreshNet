package e.user.netmodel.interfaces;

import e.user.netmodel.bean.BaseData;
import io.reactivex.disposables.Disposable;

//自定义接口返回类型
public interface IDataSubscriber<T> {
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
     * @param baseData 基础泛型
     */
    void doOnNext(BaseData<T> baseData);

    /**
     * 请求完成回调
     */
    void doOnCompleted();
}
