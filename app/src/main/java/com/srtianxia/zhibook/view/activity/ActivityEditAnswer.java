package com.srtianxia.zhibook.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.presenter.ActivityEditAnswerPresenter;
import com.srtianxia.zhibook.view.IView.IActivityEditAnswer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/23.
 */
public class ActivityEditAnswer extends BaseActivity implements IActivityEditAnswer {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ed_answer_content)
    EditText edAnswerContent;

    private ActivityEditAnswerPresenter presenter;
    private String questionId;

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
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_answer_edit,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_addPic:
                Toast.makeText(ActivityEditAnswer.this, "功能尚未添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_send:
                presenter.editAnswer();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
