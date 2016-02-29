package com.srtianxia.zhibook.model.callback;

import android.net.Uri;

/**
 * Created by srtianxia on 2016/2/28.
 */
public interface OnUpLoadPicLisener {
    /***
     * uri 本地
     * url 网络
     * @param uri
     * @param url
     */
    void success(Uri uri,String url);
    void failure(String s);
    void onProgree(int i);
}
