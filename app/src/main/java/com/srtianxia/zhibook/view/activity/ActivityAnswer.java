package com.srtianxia.zhibook.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.model.bean.zhibook.Answer;
import com.srtianxia.zhibook.presenter.GetAnswerPresenter;
import com.srtianxia.zhibook.view.IView.IActivityAnswer;
import com.srtianxia.zhibook.view.adapter.AnswerAdapter;
import com.srtianxia.zhibook.view.adapter.OnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/10.
 *  问题content在 CollapsingToolbarLayout中 回答在RecyclerView
 *  想让回答在RecyclerView响应滚动
 */
public class ActivityAnswer extends BaseActivity implements IActivityAnswer {

    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.answer_fab)
    FloatingActionButton answerFab;
    @Bind(R.id.rv_answer)
    RecyclerView rvAnswer;
    @Bind(R.id.nested_view)
    NestedScrollView nestedView;


    private AnswerAdapter adapter;
    private String questionId;
    private GetAnswerPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        questionId = getIntent().getStringExtra("questionId");
        ButterKnife.bind(this);
        initView();
        initRv();
        presenter = new GetAnswerPresenter(this);
        presenter.getAnswer();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(getString(R.string.app_name));
        nestedView.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    private void initRv() {
        adapter = new AnswerAdapter(this);
        rvAnswer.setAdapter(adapter);
        rvAnswer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
    }

    @Override
    public String getQuestionId() {
        return questionId;
    }

    @Override
    public void initAnswerSuccess(List<Answer> answers) {
        adapter.setData(answers);
    }

    @Override
    public void initAnswerFailure(String s) {

    }
}
