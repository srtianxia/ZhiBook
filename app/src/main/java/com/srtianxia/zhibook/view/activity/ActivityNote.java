package com.srtianxia.zhibook.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/17.
 */
public class ActivityNote extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab_note)
    FloatingActionButton fabNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);
        initView();
        setClick();
    }

    private void setClick() {
        fabNote.setOnClickListener(this);
    }

    private void initView() {
        toolbar.setTitle("MarkDown笔记");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_note:
                Intent intent = new Intent(this,ActivityNoteEdit.class);
                startActivity(intent);
                break;
        }
    }
}
