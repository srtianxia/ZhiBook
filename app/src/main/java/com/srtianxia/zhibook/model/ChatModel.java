package com.srtianxia.zhibook.model;

import com.srtianxia.zhibook.model.Imodel.IChatModel;
import com.srtianxia.zhibook.utils.http.RetrofitAPI;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by srtianxia on 2016/2/20.
 */
public class ChatModel implements IChatModel {
    private static ChatModel instance = new ChatModel();
    private Retrofit retrofit;
    private RetrofitAPI retrofitAPI;

    private ChatModel(){
//        retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl()
//                .build();
    }

    public static ChatModel getInstance(){
        return instance;
    }
}
