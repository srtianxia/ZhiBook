package com.srtianxia.zhibook.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.utils.md.MDReader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/1/20.
 */
public class ActivityPhoneDisplay extends BaseActivity {
    @Bind(R.id.tv_display)
    TextView tvDisplay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_display);
        ButterKnife.bind(this);
        String data = getIntent().getStringExtra("content");
        MDReader mdReader = new MDReader(data);
        tvDisplay.setTextKeepState(mdReader.getFormattedContent(), TextView.BufferType.SPANNABLE);
    }
}
