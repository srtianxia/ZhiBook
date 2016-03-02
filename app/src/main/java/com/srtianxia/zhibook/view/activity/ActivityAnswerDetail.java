package com.srtianxia.zhibook.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.bumptech.glide.Glide;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.model.bean.zhibook.Answer;
import com.srtianxia.zhibook.model.bean.zhibook.CollectFolder;
import com.srtianxia.zhibook.presenter.AnswerDetailPresenter;
import com.srtianxia.zhibook.utils.HtmlTagHandler;
import com.srtianxia.zhibook.utils.http.OkHttpUtils;
import com.srtianxia.zhibook.utils.http.callback.OkHttpUtilsCallback;
import com.srtianxia.zhibook.view.IView.IActivityAnswerDetail;
import com.srtianxia.zhibook.view.adapter.BottomSheetAdaper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

/**
 * Created by srtianxia on 2016/2/15
 */

public class ActivityAnswerDetail extends BaseActivity implements IActivityAnswerDetail,
        View.OnClickListener {
    private static final String TAG = "ActivityAnswerDetail";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.answer_question_title)
    TextView answerQuestionTitle;
    @Bind(R.id.card_answer_question_title)
    CardView cardAnswerQuestionTitle;
    @Bind(R.id.answer_detail_head)
    ImageView answerDetailHead;
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

    private Html.ImageGetter imageGetter;
    private HtmlTagHandler tagHandler;

    private Boolean ifCollect = false;
    private Boolean ifFavorite = false;
    private Boolean ifPraise = false;
    private Integer answerId;

    private AnswerDetailPresenter presenter;

    /***
     * TextView设置两次setText （图片为异步加载）
     */

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String content = (String) msg.obj;
            answerDetailContent.invalidate();
            answerDetailContent.setText(Html.fromHtml(content, imageGetter,tagHandler));
        }
    };

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
        answerDetailPraise.setOnClickListener(this);
    }

    private void initView() {
        toolbar.setTitle(getString(R.string.toolbar_answer));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        final Answer answer = (Answer) getIntent().getSerializableExtra("answerDetail");
        String questionTitle = getIntent().getStringExtra("questionTitle");
        answerQuestionTitle.setText(questionTitle);
        answerDetailPraiseCount.setText("" + answer.getPraise());
        Glide.with(this)
                .load(answer.getAnswerAuthorHead())
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .crossFade()
                .into(answerDetailHead);
        answerDetailAuthor.setText(answer.getAnswerAuthorName());

        imageGetter = new Html.ImageGetter() {
            Drawable drawable = null;
            @Override
            public Drawable getDrawable(String source) {
                OkHttpUtils.asyGet(source, new OkHttpUtilsCallback() {
                    @Override
                    public void onResponse(Response response, String status) throws IOException {
//                        drawable = Drawable.createFromStream(response.body().byteStream(), "");
                        Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                        float scaleWidth = ((float) getResources().getDisplayMetrics().widthPixels)/bitmap.getWidth();
                        // 取得想要缩放的matrix参数
                        Matrix matrix = new Matrix();
                        matrix.postScale(scaleWidth, scaleWidth);
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,
                                true);
                        drawable = new BitmapDrawable(bitmap);
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        Message message = new Message();
                        message.obj = answer.getContent();
                        handler.sendMessage(message);
                    }
                });
                return drawable;
            }
        };
//        answerDetailContent.setText(answer.getContent());
        answerDetailContent.setText(Html.fromHtml(answer.getContent(), imageGetter,tagHandler));
        answerId = answer.getId();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_bt_answer_collect:
                presenter.getFolder();
//                if (ifCollect){
//                    imgBtAnswerCollect.setImageResource(R.mipmap.ic_bookmark_outline_grey600);
//                    ifCollect = false;
//                }else {
//                    imgBtAnswerCollect.setImageResource(R.mipmap.ic_bookmark_grey600);
//                    ifCollect = true;
//                }
                BottomSheetDialog dialog = new BottomSheetDialog(this);
                View view = LayoutInflater.from(this).inflate(R.layout.ui_bottom_sheet,null);
                RecyclerView rvBottom = (RecyclerView) view.findViewById(R.id.rv_bottom_sheet);

                List<String> items = new ArrayList<>();
                for (int i=0;i<9;i++){
                    items.add("第"+i+"个item");
                }
                rvBottom.setAdapter(new BottomSheetAdaper(this,items));
                rvBottom.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                dialog.setContentView(view);
                dialog.show();
                break;
            case R.id.img_bt_answer_comment:
                break;
            case R.id.img_bt_answer_favorite:
                if (ifFavorite) {
                    imgBtAnswerFavorite.setImageResource(R.mipmap.ic_favorite_outline_grey600);
                    ifFavorite = false;
                } else {
                    imgBtAnswerFavorite.setImageResource(R.mipmap.ic_favorite_grey600);
                    ifFavorite = true;
                }
                break;
            case R.id.img_bt_answer_flag:
                break;
            case R.id.answer_detail_praise:
                if (ifPraise) {
                    Toast.makeText(ActivityAnswerDetail.this, "已经点过赞啦", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.praise(answerId);
                    ifPraise = true;
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
        Integer nowCount = Integer.valueOf(answerDetailPraiseCount.getText().toString()) + 1;
        answerDetailPraiseCount.setText(String.valueOf(nowCount));
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
        for (int i = 0; i < folders.size(); i++) {
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

    /**
     * 取消ButterKnife的解除绑定，退出时不会报空指针（会造成内存泄露，
     * 有空要研究下为什么空指针）
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ButterKnife.unbind(this);
        presenter.onRelieveView();
    }
}
