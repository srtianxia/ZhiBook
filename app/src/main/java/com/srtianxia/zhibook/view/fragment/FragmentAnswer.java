package com.srtianxia.zhibook.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.presenter.GetAnswerPresenter;
import com.srtianxia.zhibook.view.IView.IFragmentAnswer;
import com.srtianxia.zhibook.view.adapter.AnswerAdapter;
import com.srtianxia.zhibook.view.adapter.OnItemClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/8.
 * 此类废除
 * ！！！！！！！！！！！！！！！！！
 */
public class FragmentAnswer extends Fragment implements IFragmentAnswer{
//    @Bind(R.id.tv_answer_answerCount)
//    TextView tvAnswerAnswerCount;
    @Bind(R.id.rv_answer)
    RecyclerView rvAnswer;
    private View view;

    private GetAnswerPresenter presenter;
    private AnswerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_answer, container, false);
        ButterKnife.bind(this, view);
        initRv();
        presenter.getAnswer();
        return view;
    }

    private void initRv() {
        adapter = new AnswerAdapter(getActivity());
        rvAnswer.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rvAnswer.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void initAnswerSuccess() {

    }

    @Override
    public void initAnswerFailure() {

    }
}
