package com.srtianxia.zhibook.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
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

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/10.
 * 问题content在 CollapsingToolbarLayout中 回答在RecyclerView
 * 想让回答在RecyclerView响应滚动 待完成
 * ..............  2/14 4:20
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

    private AnswerAdapter adapter;
    private String questionId;
    private String questionTitle;
    private String questionContent;
    private GetAnswerPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        questionId = getIntent().getStringExtra("questionId");
        questionContent = getIntent().getStringExtra("content");
        questionTitle = getIntent().getStringExtra("title");
        ButterKnife.bind(this);
        initView();
        initRv();
        presenter = new GetAnswerPresenter(this);
        presenter.getAnswer();
    }

    private void initView() {
//        toolbar.setTitle(questionTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(questionContent);
    }

    private void initRv() {
        adapter = new AnswerAdapter(this);
        rvAnswer.setAdapter(adapter);
        rvAnswer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public String getQuestionId() {
        return questionId;
    }

    @Override
    public void initAnswerSuccess(final List<Answer> answers) {
        adapter.setData(answers);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(ActivityAnswer.this,ActivityAnswerDetail.class);
                intent.putExtra("answerDetail", (Serializable) answers.get(position));
                intent.putExtra("questionTitle",questionTitle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void initAnswerFailure(String s) {

    }
}
