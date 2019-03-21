package e.user.netmodel.bean;

public class BaseData<T> extends BaseBean {
    /**
     * 数据
     */
    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
