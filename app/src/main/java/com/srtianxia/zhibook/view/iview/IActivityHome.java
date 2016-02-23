package com.srtianxia.zhibook.view.IView;

import com.srtianxia.zhibook.model.bean.zhibook.User;

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

    void updateInfo(User user);
    void changHead(String url);
}
