package com.srtianxia.zhibook.view.iactivity;

/**
 * Created by srtianxia on 2016/1/21.
 */
public interface IActivityLogin {
    String getUsername();
    String getPassword();

    void LoginSuccess();
    void showFailureCause(String s);
}
