package com.srtianxia.zhibook.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.model.bean.ChatBean;
import com.srtianxia.zhibook.view.adapter.ChatAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/20.
 */
public class ActivityChat extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ed_chat_content)
    EditText edChatContent;
    @Bind(R.id.bt_chat_send)
    Button btChatSend;
    @Bind(R.id.rv_chat)
    RecyclerView rvChat;
    @Bind(R.id.bt_chat_send2)
    Button btChatSend2;

    private ChatAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        initView();
        adapter = new ChatAdapter(this);
        rvChat.setItemAnimator(new DefaultItemAnimator());
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvChat.setAdapter(adapter);
        rvChat.setLayoutManager(manager);
    }

    private void initView() {
        btChatSend.setOnClickListener(this);
        btChatSend2.setOnClickListener(this);
        toolbar.setTitle(getString(R.string.toolbar_chat));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        setSupportActionBar(toolbar);
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
            case R.id.bt_chat_send:
                int type = ChatBean.TYPE_RIGHT;
                String s = edChatContent.getText().toString();
                String url = "http://www.91danji.com/attachments/201509/27/13/4cevsjye7.jpg";
                adapter.addMsg(new ChatBean(s, url,type));
                break;
            case R.id.bt_chat_send2:
                String s1 = edChatContent.getText().toString();
                String url1 = "http://cdn.duitang.com/uploads/item/201412/31/20141231121257_YQAeZ.png";
                int type1 = ChatBean.TYPE_LEFT;
                adapter.addMsg(new ChatBean(s1,url1,type1));
                break;
        }
    }
}
