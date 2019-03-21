package e.user.netmodel.net;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class StringCallBack implements Callback<String> {

    public abstract void onSuccess(int statuCode,String response);

    public abstract void onFailed(Exception e);

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        onSuccess(response.code(),response.body());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        onFailed(new Exception(t));
    }
}
