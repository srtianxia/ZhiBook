package com.srtianxia.zhibook.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.presenter.LoginPresenter;
import com.srtianxia.zhibook.view.IView.IActivityLogin;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/1/21.
 */
public class ActivityLogin extends BaseActivity implements IActivityLogin,View.OnClickListener{


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_welcome)
    TextView tvWelcome;
    @Bind(R.id.ed_username)
    EditText edUsername;
    @Bind(R.id.ed_password)
    EditText edPassword;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.tv_register)
    TextView tvRegister;


    private LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
        initView();
    }

    private void initView() {
//        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        btLogin.setOnClickListener(this);
    }


    @Override
    public String getUsername() {
        return edUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return edPassword.getText().toString();
    }

    @Override
    public void LoginSuccess() {
        tvRegister.setText("登陆成功");
    }

    @Override
    public void showFailureCause(String s) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        presenter.onRelieveView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                presenter.login();
                break;
        }
    }
}

