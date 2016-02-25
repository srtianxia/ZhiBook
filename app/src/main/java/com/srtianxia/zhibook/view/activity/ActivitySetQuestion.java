package com.srtianxia.zhibook.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.presenter.SetQuestionPresenter;
import com.srtianxia.zhibook.view.IView.IActivitySetQuestion;

import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/9.
 */
public class ActivitySetQuestion extends BaseActivity implements
        View.OnClickListener, IActivitySetQuestion,CropHandler {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ed_question_title)
    EditText edQuestionTitle;
    @Bind(R.id.til_question_title)
    TextInputLayout tilQuestionTitle;
    @Bind(R.id.ed_question_content)
    EditText edQuestionContent;

    private SetQuestionPresenter presenter;

    private CropParams cropParams = new CropParams();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_question);
        presenter = new SetQuestionPresenter(this);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        toolbar.setTitle("编辑问题");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_chevron_left_grey600));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_set_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_addPic:
                //功能尚未添加，但是以前做过一次方案有bug，正在寻求更好的解决方案
//                Toast.makeText(ActivitySetQuestion.this, "功能尚未添加", Toast.LENGTH_SHORT).show();
                Intent intent = CropHelper.buildCropFromGalleryIntent(new CropParams());
                CropHelper.clearCachedCropFile(cropParams.uri);
                startActivityForResult(intent, CropHelper.REQUEST_CROP);
                break;
            case R.id.menu_item_send:
                if (edQuestionTitle.getText().length() <= 10) {
                    tilQuestionTitle.setError("问题长度过短");
                } else {
                    tilQuestionTitle.setErrorEnabled(false);
                    presenter.setQuestion();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public String getQTitle() {
        return edQuestionTitle.getText().toString();
    }

    @Override
    public String getQContent() {
        return edQuestionContent.getText().toString();
    }

    @Override
    public void setQuestionSuccess() {
        Toast.makeText(ActivitySetQuestion.this, "发布成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void setQuestionFailure() {}

    /***
     * url为上传后图片的bomb返回的图片链接
     * @param url
     */
    @Override
    public void uploadPicAfter(String url) {

    }

    /***
     * 裁剪照片后的回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropHelper.handleResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onPhotoCropped(Uri uri) {
        presenter.uploadPic(uri);
    }

    @Override
    public void onCropCancel() {

    }

    @Override
    public void onCropFailed(String message) {

    }

    @Override
    public CropParams getCropParams() {
        return cropParams;
    }

    @Override
    public Activity getContext() {
        return getContext();
    }
}
