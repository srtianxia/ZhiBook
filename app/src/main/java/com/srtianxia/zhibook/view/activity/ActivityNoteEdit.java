package com.srtianxia.zhibook.view.activity;

import android.content.Intent;
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
import com.srtianxia.zhibook.presenter.NoteEditPresenter;
import com.srtianxia.zhibook.view.IView.IActivityNoteEdit;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/17.
 */
public class ActivityNoteEdit extends BaseActivity implements View.OnClickListener,IActivityNoteEdit {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ed_note)
    EditText edNote;

    private NoteEditPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        ButterKnife.bind(this);
        presenter = new NoteEditPresenter(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle(getString(R.string.toolbar_edit_note));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveNoteToDB();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_note_edit,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_preview:
                Intent intent = new Intent(this,ActivityPhoneDisplay.class);
                intent.putExtra("content",edNote.getText().toString());
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getNoteContent() {
        return edNote.getText().toString();
    }

    @Override
    public void saveDBsuccess() {
        Toast.makeText(ActivityNoteEdit.this, "success", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void saveDBfailure() {
        Toast.makeText(ActivityNoteEdit.this, "failure", Toast.LENGTH_SHORT).show();
    }
}
