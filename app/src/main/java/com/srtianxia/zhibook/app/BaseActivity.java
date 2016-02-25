package com.srtianxia.zhibook.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.srtianxia.zhibook.R;

/**
 * Created by srtianxia on 2016/1/20.
 */
public class BaseActivity extends AppCompatActivity {
    private MaterialDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showProgress(String title) {
        dialog = new MaterialDialog.Builder(this)
                .title(title)
                .content("请稍候")
                .theme(Theme.LIGHT)
                .backgroundColor(getResources().getColor(R.color.colorWhite))
                .progress(true, 100)
                .cancelable(false)
                .show();
    }

    public void dismissProgress() {
        dialog.dismiss();
    }
}
