package com.srtianxia.zhibook.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.model.bean.zhibook.Note;
import com.srtianxia.zhibook.view.IView.IActivityNote;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/17.
 */
public class ActivityNote extends BaseActivity implements IActivityNote,View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab_note)
    FloatingActionButton fabNote;
    @Bind(R.id.rv_note)
    RecyclerView rvNote;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

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
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initView() {
        toolbar.setTitle(getString(R.string.toolbar_note));
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
        switch (v.getId()) {
            case R.id.fab_note:
                Intent intent = new Intent(this, ActivityNoteEdit.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void showNoteSuccess(List<Note> notes) {

    }
}
