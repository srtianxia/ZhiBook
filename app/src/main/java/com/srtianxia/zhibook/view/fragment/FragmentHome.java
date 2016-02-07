package com.srtianxia.zhibook.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.API;
import com.srtianxia.zhibook.model.bean.zhibook.QuestionHolder;
import com.srtianxia.zhibook.utils.http.OkHttpUtils;
import com.srtianxia.zhibook.utils.http.callback.OkHttpUtilsCallback;
import com.srtianxia.zhibook.view.adapter.OnItemClickListener;
import com.srtianxia.zhibook.view.adapter.QuestionAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by srtianxia on 2016/2/6.
 */
public class FragmentHome extends Fragment implements View.OnClickListener {
    @Bind(R.id.rv_home_question)
    RecyclerView rvHomeQuestion;
    @Bind(R.id.home_fab)
    FloatingActionButton fab;
    private View view;
    private QuestionAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Gson gson = new Gson();
            QuestionHolder questionHolder = gson.fromJson((String) msg.obj, QuestionHolder.class);
            adapter = new QuestionAdapter(getActivity(), questionHolder.getQuestions());
            rvHomeQuestion.setAdapter(adapter);
            rvHomeQuestion.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onClick(View view, int position) {

                }

                @Override
                public void onLongClick(View view, int position) {

                }
            });
            rvHomeQuestion.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        fab.setOnClickListener(this);
        OkHttpUtils.asyGet(API.getQuestion, new OkHttpUtilsCallback() {
            @Override
            public void onResponse(String response, String status) {
                Message message = new Message();
                message.obj = response;
                handler.sendMessage(message);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                break;
        }
    }
}
