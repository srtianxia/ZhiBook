package com.srtianxia.zhibook.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhibook.CollectFolderBean;
import com.srtianxia.zhibook.presenter.CollectPresenter;
import com.srtianxia.zhibook.view.IView.IFragmentCollect;
import com.srtianxia.zhibook.view.adapter.CollectAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/15.
 */
public class FragmentCollect extends Fragment implements IFragmentCollect,View.OnClickListener{
    @Bind(R.id.rv_question_collect)
    RecyclerView rvQuestionCollect;
    private View view;
    private CollectPresenter presenter;

    private CollectAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_collect, container, false);
        presenter = new CollectPresenter(this);
        ButterKnife.bind(this, view);
        initView();
        getActivity().findViewById(R.id.find_fab).setOnClickListener(this);
        presenter.getCollectionFolder();
        return view;
    }

    private void initView() {
        adapter = new CollectAdapter(getActivity());
        rvQuestionCollect.setAdapter(adapter);
        rvQuestionCollect.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showCollection(CollectFolderBean bean) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.find_fab:
                new MaterialDialog.Builder(getActivity())
                        .theme(Theme.LIGHT)
                        .title("创建文件夹")
                        .cancelable(false)
                        .negativeText("取消")
                        .positiveText("确定")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input("文件夹名", null,false, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                presenter.addFolder("123", String.valueOf(input));
                            }
                        }).show();
                break;
        }
    }
}
