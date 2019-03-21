package e.user.netmodel.net;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceInterface {

    //获取豆瓣Top250 榜单
    @GET("top250")
    Call<String> getTop250 (@Query("start") int start , @Query("count") int count);

    @GET("top250")
    Observable<String> getTop250RxJava(@Query("start") int start , @Query("count") int count);
}
