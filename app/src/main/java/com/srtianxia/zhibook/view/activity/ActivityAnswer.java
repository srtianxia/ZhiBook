package com.srtianxia.zhibook.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.API;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.model.bean.zhibook.Answer;
import com.srtianxia.zhibook.model.bean.zhibook.AnswerHolder;
import com.srtianxia.zhibook.utils.http.OkHttpUtils;
import com.srtianxia.zhibook.utils.http.callback.OkHttpUtilsCallback;
import com.srtianxia.zhibook.view.adapter.AnswerAdapter;
import com.srtianxia.zhibook.view.adapter.OnItemClickListener;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

/**
 * Created by srtianxia on 2016/2/10.
 */
public class ActivityAnswer extends BaseActivity {
    @Bind(R.id.tv_answer_answerCount)
    TextView tvAnswerAnswerCount;
    @Bind(R.id.rv_answer)
    RecyclerView rvAnswer;
    private AnswerAdapter adapter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            List<Answer> answers = (List<Answer>) msg.obj;
            adapter = new AnswerAdapter(ActivityAnswer.this, answers);
            rvAnswer.setAdapter(adapter);
            rvAnswer.setLayoutManager(new LinearLayoutManager(ActivityAnswer.this,LinearLayoutManager.VERTICAL,false));
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onClick(View view, int position) {

                }

                @Override
                public void onLongClick(View view, int position) {

                }
            });
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        ButterKnife.bind(this);
        String questionId = getIntent().getStringExtra("questionId");
        OkHttpUtils.asyPost(API.getAnswer, new OkHttpUtilsCallback() {
            @Override
            public void onResponse(Response response, String status) throws IOException {
                if (status.equals("OK")) {
                    Gson gson = new Gson();
                    AnswerHolder answerHolder = gson.fromJson(response.body().charStream(), AnswerHolder.class);
                    Message message = new Message();
                    message.obj = answerHolder.getAnswers();
                    handler.sendMessage(message);
                }
            }
        },new OkHttpUtils.Param("questionId",questionId));
    }
}
