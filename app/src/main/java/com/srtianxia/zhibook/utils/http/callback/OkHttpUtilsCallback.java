package com.srtianxia.zhibook.utils.http.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by srtianxia on 2016/2/6.
 */
public interface OkHttpUtilsCallback {
    void onResponse(Response response, String status) throws IOException;
}
