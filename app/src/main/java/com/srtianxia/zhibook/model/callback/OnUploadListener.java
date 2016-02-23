package com.srtianxia.zhibook.model.callback;

/**
 * Created by srtianxia on 2016/2/22.
 */
public interface OnUploadListener {
    void success(String url);
    void progress(int progress);
    void failure();
}
