package com.srtianxia.zhibook.model.callback;

/**
 * Created by srtianxia on 2016/1/21.
 */
public interface OnLoginListener {
    void loginSuccess();

    void loginFailure(int i, String s);
}
