package com.srtianxia.zhibook.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.facebook.drawee.view.SimpleDraweeView;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.model.bean.zhibook.Answer;
import com.srtianxia.zhibook.model.bean.zhibook.CollectFolder;
import com.srtianxia.zhibook.presenter.AnswerDetailPresenter;
import com.srtianxia.zhibook.view.IView.IActivityAnswerDetail;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/15.
 *
 */

public class ActivityAnswerDetail extends BaseActivity implements IActivityAnswerDetail,View.OnClickListener {
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
    @Bind(R.id.img_bt_answer_flag)
    ImageView imgBtAnswerFlag;
    @Bind(R.id.img_bt_answer_favorite)
    ImageView imgBtAnswerFavorite;
    @Bind(R.id.img_bt_answer_collect)
    ImageView imgBtAnswerCollect;
    @Bind(R.id.img_bt_answer_comment)
    ImageView imgBtAnswerComment;

    private static final String TAG = "ActivityAnswerDetail";

    private Boolean ifCollect =  false;
    private Boolean ifFavorite = false;
    private Boolean ifPraise = false;

    private AnswerDetailPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_detail);
        ButterKnife.bind(this);
        presenter = new AnswerDetailPresenter(this);
        initData();
        initView();
        setClick();
    }

    private void setClick() {
        imgBtAnswerCollect.setOnClickListener(this);
        imgBtAnswerComment.setOnClickListener(this);
        imgBtAnswerFavorite.setOnClickListener(this);
        imgBtAnswerFlag.setOnClickListener(this);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_bt_answer_collect:
                presenter.getFolder();
//                if (ifCollect){
//                    imgBtAnswerCollect.setImageResource(R.mipmap.ic_bookmark_outline_grey600);
//                    ifCollect = false;
//                }else {
//                    imgBtAnswerCollect.setImageResource(R.mipmap.ic_bookmark_grey600);
//                    ifCollect = true;
//                }
                break;
            case R.id.img_bt_answer_comment:
                break;
            case R.id.img_bt_answer_favorite:
                if (ifFavorite){
                    imgBtAnswerFavorite.setImageResource(R.mipmap.ic_favorite_outline_grey600);
                    ifFavorite = false;
                }else {
                    imgBtAnswerFavorite.setImageResource(R.mipmap.ic_favorite_grey600);
                    ifFavorite = true;
                }
                break;
            case R.id.img_bt_answer_flag:
                break;
            case R.id.answer_detail_praise:
                if (ifPraise){

                }else {

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void showAddCollectSuccess() {

    }

    @Override
    public void showAddCollectFailure() {

    }

    @Override
    public void showAddPraise() {
        Toast.makeText(ActivityAnswerDetail.this, "+1", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showReducePraise() {
        Toast.makeText(ActivityAnswerDetail.this, "-1", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPraiseFailure(String s) {

    }

    @Override
    public void showFolders(List<CollectFolder> folders) {
        String f[] = new String[folders.size()];
        for (int i = 0;i<folders.size();i++){
            f[i] = folders.get(i).getFolder();
        }
        new MaterialDialog.Builder(this)
                .title("选择文件夹")
                .items(f)
                .theme(Theme.LIGHT)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {

                    }
                }).show();
    }
}
