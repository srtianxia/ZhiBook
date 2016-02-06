package com.srtianxia.zhibook.model;

import com.srtianxia.zhibook.model.Imodel.IAccountModel;
import com.srtianxia.zhibook.model.callback.OnLoginListener;
import com.srtianxia.zhibook.utils.http.OkHttpUtils;

/**
 * Created by srtianxia on 2016/1/21.
 */
public class AccountModel implements IAccountModel {
    private static AccountModel accountModel = new AccountModel();

    public static AccountModel getAccountModel() {
        return accountModel;
    }

    private AccountModel(){}


    @Override
    public void login(String username, String password, OnLoginListener onLoginListener) {

    }
}
