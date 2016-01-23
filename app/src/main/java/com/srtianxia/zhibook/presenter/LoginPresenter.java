package com.srtianxia.zhibook.presenter;

import com.srtianxia.zhibook.model.AccountModel;
import com.srtianxia.zhibook.model.IAccount.IAccountModel;
import com.srtianxia.zhibook.model.callback.OnLoginListener;
import com.srtianxia.zhibook.view.iactivity.IActivityLogin;

/**
 * Created by srtianxia on 2016/1/21.
 */
public class LoginPresenter {
    private IActivityLogin iActivityLogin;
    private IAccountModel iAccountModel;

    public LoginPresenter(IActivityLogin iActivityLogin){
        this.iActivityLogin = iActivityLogin;
        iAccountModel = AccountModel.getAccountModel();
    }

    public void login(){
        iAccountModel.login(iActivityLogin.getUsername(),
                iActivityLogin.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess() {

            }

            @Override
            public void loginFailure(int i, String s) {

            }
        });
    }
}
