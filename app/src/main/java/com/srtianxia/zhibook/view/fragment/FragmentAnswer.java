package com.srtianxia.zhibook.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.API;
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
 * Created by srtianxia on 2016/2/8.
 */
public class FragmentAnswer extends Fragment {
//    @Bind(R.id.tv_answer_answerCount)
//    TextView tvAnswerAnswerCount;
    @Bind(R.id.rv_answer)
    RecyclerView rvAnswer;
    private View view;

    private AnswerAdapter adapter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.d("123","handleMessage");
            List<Answer> answers = (List<Answer>) msg.obj;
            adapter = new AnswerAdapter(getActivity(), answers);
            rvAnswer.setAdapter(adapter);
            rvAnswer.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_answer, container, false);
        ButterKnife.bind(this, view);
        OkHttpUtils.asyPost(API.getAnswer, new OkHttpUtilsCallback() {
            @Override
            public void onResponse(Response response, String status) throws IOException {
                Gson gson = new Gson();
                AnswerHolder answerHolder = gson.fromJson(response.body().string(),AnswerHolder.class);
                Message message = new Message();
                message.obj = answerHolder.getAnswers();
                handler.sendMessage(message);
            }
        },new OkHttpUtils.Param("questionId","4"));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
