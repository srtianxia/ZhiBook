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
import com.srtianxia.zhibook.presenter.ChatPresenter;
import com.srtianxia.zhibook.utils.SharedPreferenceUtils;
import com.srtianxia.zhibook.view.IView.IActivityChat;
import com.srtianxia.zhibook.view.adapter.ChatAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/20.
 */
public class ActivityChat extends BaseActivity implements IActivityChat, View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_chat)
    RecyclerView rvChat;
    @Bind(R.id.ed_chat_msg)
    EditText edChatMsg;
    @Bind(R.id.bt_chat_send)
    Button btChatSend;
    private ChatAdapter adapter;
    private LinearLayoutManager manager;


    private ChatPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        presenter = new ChatPresenter(this);
        initView();
        adapter = new ChatAdapter(this);
        rvChat.setItemAnimator(new DefaultItemAnimator());
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvChat.setAdapter(adapter);
        rvChat.setLayoutManager(manager);

        initChatData();
    }

    private void initChatData() {
        ChatBean tempBean = new ChatBean();
        int type = ChatBean.TYPE_LEFT;
        tempBean.setText("我是人见人爱的吵吵，欢迎调戏~");
        tempBean.setType(type);
        adapter.addMsg(tempBean);
    }

    private void initView() {
        btChatSend.setOnClickListener(this);
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
                ChatBean tempBean = new ChatBean();
                int type = ChatBean.TYPE_RIGHT;
                String s = edChatMsg.getText().toString();
                String url = SharedPreferenceUtils.gethead();
                tempBean.setText(s);
                tempBean.setHeadUrl(url);
                tempBean.setType(type);
                adapter.addMsg(tempBean);
                presenter.sendMsg(tempBean);
                break;
        }
    }


    @Override
    public void onResponse(ChatBean chatBean) {
        ChatBean tempBean = new ChatBean();
        int type = ChatBean.TYPE_LEFT;
        String s = chatBean.getText();
        tempBean.setText(s);
        tempBean.setType(type);
        adapter.addMsg(tempBean);
        rvChat.smoothScrollToPosition(adapter.getItemCount());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onRelieveView();
    }
}
