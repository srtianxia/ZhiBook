package com.srtianxia.zhibook.utils.cache;

import android.graphics.Bitmap;

/**
 * Created by srtianxia on 2016/1/22.
 */
public interface ImageCache {
    Bitmap get(String url);
    void put(String url, Bitmap bitmap);
}
