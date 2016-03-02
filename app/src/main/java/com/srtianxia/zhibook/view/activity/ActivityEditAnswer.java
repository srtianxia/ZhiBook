package com.srtianxia.zhibook.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.presenter.ActivityEditAnswerPresenter;
import com.srtianxia.zhibook.view.IView.IActivityEditAnswer;

import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;

import java.io.FileNotFoundException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/23.
 */
public class ActivityEditAnswer extends BaseActivity implements IActivityEditAnswer,CropHandler {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ed_answer_content)
    EditText edAnswerContent;

    private ActivityEditAnswerPresenter presenter;
    private String questionId;

    private CropParams cropParams = new CropParams();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_answer);
        presenter = new ActivityEditAnswerPresenter(this);
        questionId = getIntent().getStringExtra("questionId");
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        toolbar.setTitle(getString(R.string.toolbar_answer_edit));
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
    public String getAcontent() {
        return edAnswerContent.getText().toString();
    }

    @Override
    public String getQuestionId() {
        return questionId;
    }

    @Override
    public void showEditSuccess() {
        finish();
    }

    @Override
    public void upLoadPicAfter(String url, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver()
                    .openInputStream(uri));
            float scaleWidth = ((float) getResources().getDisplayMetrics().widthPixels)/bitmap.getWidth();
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleWidth);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,
                    true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SpannableString mSpan1 = new SpannableString("<img src=\"" + url + "\" />");
        int start = edAnswerContent.getSelectionStart();
        mSpan1.setSpan(new ImageSpan(bitmap) , mSpan1.length() - url.length() - 14, mSpan1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if(edAnswerContent != null) {
            Editable et = edAnswerContent.getText();
            et.insert(start, mSpan1);
            edAnswerContent.setText(et);
            edAnswerContent.setSelection(start + mSpan1.length());
        }
        edAnswerContent.setLineSpacing(10f, 1f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_answer_edit,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_addPic:
//                Toast.makeText(ActivityEditAnswer.this, "功能尚未添加", Toast.LENGTH_SHORT).show();
                Intent intent = CropHelper.buildCropFromGalleryIntent(new CropParams());
                CropHelper.clearCachedCropFile(cropParams.uri);
                startActivityForResult(intent, CropHelper.REQUEST_CROP);
                break;
            case R.id.menu_item_send:
                presenter.editAnswer();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropHelper.handleResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onPhotoCropped(Uri uri) {
        presenter.upLoadPic(uri                                                                                                                                                                            );
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
