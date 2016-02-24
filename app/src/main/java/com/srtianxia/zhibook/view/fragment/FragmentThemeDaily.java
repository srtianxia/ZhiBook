package com.srtianxia.zhibook.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhihu.ThemeDailyBean;
import com.srtianxia.zhibook.presenter.FragmentThemeDailyPresenter;
import com.srtianxia.zhibook.view.IView.IFragmentThemeDaily;
import com.srtianxia.zhibook.view.adapter.OnItemClickListener;
import com.srtianxia.zhibook.view.adapter.ThemeAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/24.
 */
public class FragmentThemeDaily extends Fragment implements IFragmentThemeDaily,View.OnClickListener{
    @Bind(R.id.rv_theme_daily)
    RecyclerView rvThemeDaily;
    private View view;
    private FloatingActionButton fab;

    private ThemeAdapter adapter;
    private LinearLayoutManager manager;
    private FragmentThemeDailyPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_theme, container, false);
        ButterKnife.bind(this, view);
        presenter = new FragmentThemeDailyPresenter(this);
        presenter.getThemeList();
        fab = (FloatingActionButton) getActivity().findViewById(R.id.find_fab);
        fab.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void showThemeList(ThemeDailyBean bean) {
        adapter = new ThemeAdapter(getActivity(),bean.getOthers());
        manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvThemeDaily.setLayoutManager(manager);
        rvThemeDaily.setAdapter(adapter);
        rvThemeDaily.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.find_fab:

                break;
        }
    }

    private void hideViews() {
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        fab.animate().translationY(fab.getHeight() + fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }
}
