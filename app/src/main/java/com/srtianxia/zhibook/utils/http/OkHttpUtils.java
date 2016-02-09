package com.srtianxia.zhibook.utils.http;

import android.util.Log;

import com.srtianxia.zhibook.utils.http.callback.OkHttpUtilsCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by srtianxia on 2016/2/6.
 */
public class OkHttpUtils {
    private static final String TAG = "OkHttpUtils";

    private static OkHttpUtils instance;
    private OkHttpClient client;

    public static OkHttpUtils getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtils.class) {
                if (instance == null) {
                    instance = new OkHttpUtils();
                }
            }
        }
        return instance;
    }

    private OkHttpUtils(){
        client = new OkHttpClient();
    }
    //异步get
    public static void asyGet(String url, OkHttpUtilsCallback callback){
        getInstance()._asyGet(url,callback);
    }

    private void _asyGet(String url, final OkHttpUtilsCallback callback){
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onResponse(response,response.message());
            }
        });
    }
    //异步post
    public static void asyPost(String url,OkHttpUtilsCallback callback,Param...params){
        getInstance()._asyPost(url,callback,params);
    }

    private void _asyPost(String url, final OkHttpUtilsCallback callback, Param...params){
        final Request request = buildRequest(url,params);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onResponse(response,response.message());
            }
        });
    }

    private Request buildRequest(String url,Param[] params){
        if (params == null){
            params = new Param[0];
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Param param : params){
            builder.add(param.key,param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }
    public static class Param {
        String key;
        String value;

        public Param() {}
        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
