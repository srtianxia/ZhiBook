package com.srtianxia.zhibook.model;

import android.util.Log;

import com.srtianxia.zhibook.model.Imodel.IChatModel;
import com.srtianxia.zhibook.model.bean.ChatBean;
import com.srtianxia.zhibook.model.callback.OnChatListener;
import com.srtianxia.zhibook.utils.http.RetrofitAPI;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by srtianxia on 2016/2/20.
 */
public class ChatModel implements IChatModel {
    private static final String TAG = "ChatModel";

    private static ChatModel instance = new ChatModel();
    private Retrofit retrofit;
    private RetrofitAPI retrofitAPI;

    private ChatModel(){
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(RetrofitAPI.BASIC_URL)
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }

    public static ChatModel getInstance(){
        return instance;
    }


    @Override
    public void postMsg(String msg, String apiKey, final OnChatListener listener) {
        retrofitAPI.getMsg("b68e170aca7687d9c0737d4f16feb72d",msg)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChatBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.getMessage());
                    }

                    @Override
                    public void onNext(ChatBean chatBean) {
                        listener.success(chatBean);
                    }
                });
    }
}
