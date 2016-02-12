package com.srtianxia.zhibook.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/12.
 */
public class ActivityDailyContent extends BaseActivity {
    @Bind(R.id.wv_daily_content)
    WebView wvDailyContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_content);
        ButterKnife.bind(this);
    }
}
