package com.srtianxia.zhibook.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.presenter.GetQuestionPresenter;
import com.srtianxia.zhibook.view.IView.IFragmentQuestion;
import com.srtianxia.zhibook.view.activity.ActivitySetQuestion;
import com.srtianxia.zhibook.view.adapter.QuestionAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by srtianxia on 2016/2/6.
 */

public class FragmentQuestion extends Fragment implements View.OnClickListener,IFragmentQuestion {
    @Bind(R.id.rv_home_question)
    RecyclerView rvHomeQuestion;
    @Bind(R.id.home_fab)
    FloatingActionButton fab;


    private GetQuestionPresenter presenter;
    private View view;
    private QuestionAdapter adapter;

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Gson gson = new Gson();
//            QuestionHolder questionHolder = gson.fromJson((String) msg.obj, QuestionHolder.class);
//            adapter = new QuestionAdapter(getActivity(), questionHolder.getQuestions());
//            rvHomeQuestion.setAdapter(adapter);
//            rvHomeQuestion.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//            adapter.setOnItemClickListener(new OnItemClickListener() {
//                @Override
//                public void onClick(View view, int position) {
//                    Intent intent = new Intent(getActivity(), ActivityAnswer.class);
//                    intent.putExtra("questionId",String.valueOf(position+1));
//                    startActivity(intent);
//                    Log.d("position : ", String.valueOf(position));
//                }
//
//                @Override
//                public void onLongClick(View view, int position) {
//
//                }
//            });
//            rvHomeQuestion.addOnScrollListener(new HidingScrollListener() {
//                @Override
//                public void onHide() {
//                    hideViews();
//                }
//
//                @Override
//                public void onShow() {
//                    showViews();
//                }
//            });
//        }
//    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        presenter = new GetQuestionPresenter(this);
        setOnClick();
        presenter.getQuestion();
//        OkHttpUtils.asyGet(API.getQuestion, new OkHttpUtilsCallback() {
//            @Override
//            public void onResponse(Response response, String status) throws IOException {
//                Message message = new Message();
//                message.obj = response.body().string();
//                handler.sendMessage(message);
//            }
//        });
        return view;
    }

    private void setOnClick() {
        fab.setOnClickListener(this);
    }

    private void hideViews() {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) fab.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        fab.animate().translationY(fab.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_fab:
                Intent intent = new Intent(getActivity(), ActivitySetQuestion.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showInitSuccess() {

    }

    @Override
    public void showInitFailure() {

    }
}
