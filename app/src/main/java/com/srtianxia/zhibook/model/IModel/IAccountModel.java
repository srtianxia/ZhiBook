package com.srtianxia.zhibook.model.Imodel;

import com.srtianxia.zhibook.model.callback.OnLoginListener;
import com.srtianxia.zhibook.model.callback.OnRegisterListener;
import com.srtianxia.zhibook.model.callback.OnUpdateInfoListener;

/**
 * Created by srtianxia on 2016/1/21.
 */
public interface IAccountModel {
    void login(String username, String password, OnLoginListener onLoginListener);
    void register(String username, String password, OnRegisterListener listener);

    void updateUserInfo(OnUpdateInfoListener listener);
}
