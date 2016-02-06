package com.srtianxia.zhibook.model.Imodel;

import com.srtianxia.zhibook.model.callback.OnLoginListener;

/**
 * Created by srtianxia on 2016/1/21.
 */
public interface IAccountModel {
    void login(String username, String password, OnLoginListener onLoginListener);
}
