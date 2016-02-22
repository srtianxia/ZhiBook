package com.srtianxia.zhibook.model;

import android.os.Handler;

import com.srtianxia.zhibook.app.API;
import com.srtianxia.zhibook.model.Imodel.IAccountModel;
import com.srtianxia.zhibook.model.bean.zhibook.User;
import com.srtianxia.zhibook.model.callback.OnLoginListener;
import com.srtianxia.zhibook.model.callback.OnRegisterListener;
import com.srtianxia.zhibook.model.callback.OnUpdateInfoListener;
import com.srtianxia.zhibook.utils.SharedPreferenceUtils;
import com.srtianxia.zhibook.utils.http.OkHttpUtils;
import com.srtianxia.zhibook.utils.http.RetrofitAPI;
import com.srtianxia.zhibook.utils.http.callback.OkHttpUtilsCallback;

import java.io.IOException;

import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by srtianxia on 2016/1/21.
 */
public class AccountModel implements IAccountModel {
    private final String TAG = "AccountModel";
    private static AccountModel accountModel = new AccountModel();
    private Retrofit retrofit;
    private RetrofitAPI retrofitAPI;

    public static AccountModel getAccountModel() {
        return accountModel;
    }

    private AccountModel(){
        retrofit = new Retrofit.Builder().
                baseUrl(RetrofitAPI.BASIC_URL).
                addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }


    @Override
    public void login(final String username, String password, final OnLoginListener onLoginListener) {
        retrofitAPI.getLoginUser(username,password).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onLoginListener.loginFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        SharedPreferenceUtils.set(user.getToken(), String.valueOf(user.getId())
                                ,user.getHeadurl(),user.getName());
                        onLoginListener.loginSuccess();
                    }
                });
    }

    @Override
    public void register(String username, String password, final OnRegisterListener listener) {
        final Handler handler = new Handler();
        OkHttpUtils.asyPost(API.register, new OkHttpUtilsCallback() {
            @Override
            public void onResponse(final Response response, String status) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (response.code() == 200){
                            listener.success();
                        }else if (response.code() == 400){
                            listener.failure();
                        }
                    }
                });
            }
        },new OkHttpUtils.Param("name",username),new OkHttpUtils.Param("password",password));
    }

    @Override
    public void updateUserInfo(OnUpdateInfoListener listener) {
        User user = new User();
        user.setName(SharedPreferenceUtils.getName());
        user.setHeadurl(SharedPreferenceUtils.gethead());
        listener.success(user);
    }
}
