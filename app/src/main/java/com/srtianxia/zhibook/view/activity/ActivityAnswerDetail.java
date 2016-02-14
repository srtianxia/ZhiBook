package com.srtianxia.zhibook.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.model.bean.zhibook.Answer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/15.
 */
public class ActivityAnswerDetail extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.answer_question_title)
    TextView answerQuestionTitle;
    @Bind(R.id.card_answer_question_title)
    CardView cardAnswerQuestionTitle;
    @Bind(R.id.answer_detail_head)
    SimpleDraweeView answerDetailHead;
    @Bind(R.id.answer_detail_author)
    TextView answerDetailAuthor;
    @Bind(R.id.answer_detail_author_sign)
    TextView answerDetailAuthorSign;
    @Bind(R.id.answer_detail_praise)
    ImageView answerDetailPraise;
    @Bind(R.id.answer_detail_content)
    TextView answerDetailContent;
    @Bind(R.id.answer_detail_praise_count)
    TextView answerDetailPraiseCount;
    @Bind(R.id.answer_detail_date)
    TextView answerDetailDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        toolbar.setTitle(getString(R.string.toolbar_answer));
        setSupportActionBar(toolbar);
    }

    private void initData() {
        Answer answer = (Answer) getIntent().getSerializableExtra("answerDetail");
        String questionTitle = getIntent().getStringExtra("questionTitle");
        answerQuestionTitle.setText(questionTitle);
        answerDetailPraiseCount.setText("" + answer.getPraise());
        answerDetailHead.setImageURI(Uri.parse(answer.getAnswerAuthorHead()));
        answerDetailAuthor.setText(answer.getAnswerAuthorName());
        answerDetailContent.setText(answer.getContent());
    }
}
