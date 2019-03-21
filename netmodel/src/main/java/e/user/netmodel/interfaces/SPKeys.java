package e.user.netmodel.interfaces;

//存放SharedPreferences文件的所有Key值
public interface SPKeys {
    /**
     * 保存cookie
     */
    String TOKEN = "token";
    /**
     * 服务器相应时间
     */
    String DATE = "date";

    /**
     * 用户id
     */
    String USERID = "userid";
    /**
     * 微信用户名
     */
    String NICKNAME = "nickName";
    /**
     * 微信用户头像
     */
    String HEADIMGURL = "headImgUrl";
    /**
     * 登录成功后的token
     */
    String CS_TOKEN = "cs_token";
}
