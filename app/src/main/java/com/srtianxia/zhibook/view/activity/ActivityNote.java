package com.srtianxia.zhibook.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.model.bean.zhibook.Note;
import com.srtianxia.zhibook.presenter.NotePresenter;
import com.srtianxia.zhibook.utils.ui.DividerItemDecoration;
import com.srtianxia.zhibook.utils.ui.SimpleItemTouchHelperCallback;
import com.srtianxia.zhibook.view.IView.IActivityNote;
import com.srtianxia.zhibook.view.adapter.NoteAdapter;
import com.srtianxia.zhibook.view.adapter.OnItemClickListener;

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

    private NoteAdapter adapter;
    private LinearLayoutManager manager;
    private NotePresenter presenter;

    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        presenter = new NotePresenter(this);
        ButterKnife.bind(this);
        initView();
        setClick();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getNote();
    }

    private void setClick() {
        fabNote.setOnClickListener(this);
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
        adapter = new NoteAdapter(this,notes);
        manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvNote.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        rvNote.setAdapter(adapter);
        rvNote.setLayoutManager(manager);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(rvNote);
    }
}
