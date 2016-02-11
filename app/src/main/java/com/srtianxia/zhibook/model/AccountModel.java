package com.srtianxia.zhibook.model;

import android.util.Log;

import com.srtianxia.zhibook.model.Imodel.IAccountModel;
import com.srtianxia.zhibook.model.bean.zhibook.User;
import com.srtianxia.zhibook.model.callback.OnLoginListener;
import com.srtianxia.zhibook.utils.http.RetrofitAPI;

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
//        Call<User> call = retrofitAPI.getLoginUser(username,password);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Log.d(TAG, "token "+response.code());
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Log.d(TAG,t.getMessage());
//            }
//        });
        retrofitAPI.getLoginUser(username,password).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG,user.getToken());
                    }
                });
    }
}
