package com.srtianxia.zhibook.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.utils.markdown.MDReader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/1/20.
 */
public class ActivityPhoneDisplay extends BaseActivity {
    @Bind(R.id.tv_display)
    TextView tvDisplay;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_display);
        ButterKnife.bind(this);
        initView();
        String data = getIntent().getStringExtra("content");
        MDReader mdReader = new MDReader(data);
        tvDisplay.setTextKeepState(mdReader.getFormattedContent(), TextView.BufferType.SPANNABLE);
    }

    private void initView() {
        toolbar.setTitle(getString(R.string.toolbar_display));
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_display,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_save:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
