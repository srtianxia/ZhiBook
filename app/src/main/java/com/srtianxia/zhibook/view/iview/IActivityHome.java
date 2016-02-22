package com.srtianxia.zhibook.view.IView;

/**
 * Created by srtianxia on 2016/2/22.
 */
public interface IActivityHome {
    String getUsername();
    String getPassword();

    void loginSuccess();
    void loginFailure();

    void registerSuccess();
    void registerFailure();
}
