package com.srtianxia.zhibook.presenter;

import android.net.Uri;

import com.srtianxia.zhibook.model.AccountModel;
import com.srtianxia.zhibook.model.Imodel.IAccountModel;
import com.srtianxia.zhibook.model.Imodel.IZhiBookModel;
import com.srtianxia.zhibook.model.ZhiBookModel;
import com.srtianxia.zhibook.model.callback.OnLoginListener;
import com.srtianxia.zhibook.model.callback.OnRegisterListener;
import com.srtianxia.zhibook.model.callback.OnUploadListener;
import com.srtianxia.zhibook.view.IView.IActivityHome;

/**
 * Created by srtianxia on 2016/2/22.
 */
public class HomePresenter {
    private IActivityHome iActivityHome;
    private IZhiBookModel iZhiBookModel;
    private IAccountModel iAccountModel;

    public HomePresenter(IActivityHome iActivityHome){
        this.iActivityHome = iActivityHome;
        iZhiBookModel = ZhiBookModel.getInstance();
        iAccountModel = AccountModel.getAccountModel();
    }

    public void upLoadHead(Uri uri){
        iZhiBookModel.upLoadHead(uri, "spToken", new OnUploadListener() {
            @Override
            public void success() {

            }

            @Override
            public void progress(int progress) {

            }

            @Override
            public void failure() {

            }
        });
    }

    public void login(){
        iAccountModel.login(iActivityHome.getUsername(), iActivityHome.getPassword(),
                new OnLoginListener() {
            @Override
            public void loginSuccess() {
                iActivityHome.loginSuccess();
            }

            @Override
            public void loginFailure(String s) {
                iActivityHome.loginFailure();
            }
        });
    }

    public void register(){
        iAccountModel.register(iActivityHome.getUsername(), iActivityHome.getPassword(), new OnRegisterListener() {
            @Override
            public void success() {

            }

            @Override
            public void failure() {

            }
        });
    }
}
