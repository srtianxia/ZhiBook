package com.srtianxia.zhibook.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhihu.BannerData;
import com.srtianxia.zhibook.model.bean.zhihu.DailyBean;
import com.srtianxia.zhibook.presenter.DailyPresenter;
import com.srtianxia.zhibook.utils.ui.DividerItemDecoration;
import com.srtianxia.zhibook.utils.ui.Banner;
import com.srtianxia.zhibook.view.IView.IFragmentDaily;
import com.srtianxia.zhibook.view.activity.ActivityDailyContent;
import com.srtianxia.zhibook.view.adapter.DailyAdapter;
import com.srtianxia.zhibook.view.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/1/23.
 */
public class FragmentDaily extends Fragment implements IFragmentDaily {
    private static final String TAG = "FragmentDaily";
    @Bind(R.id.rv_daily)
    RecyclerView rvDaily;
    private DailyPresenter presenter;

    private DailyAdapter adapter;
    private LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhihu_main, container, false);
        this.inflater = inflater;
        presenter = new DailyPresenter(this);
        ButterKnife.bind(this, view);
        initRvSw();
        presenter.initData();
        return view;
    }

    private void initRvSw() {}

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showDaily(final DailyBean bean) {
//        Log.d(TAG,bean.getTopStories().get(0).getImage());
        adapter = new DailyAdapter(getActivity(),bean.getStories());
        rvDaily.setAdapter(adapter);
        rvDaily.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ActivityDailyContent.class);
                intent.putExtra("id",String.valueOf(bean.getStories().get(position).getId()));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        rvDaily.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        View v = inflater.inflate(R.layout.header_banner,rvDaily,false);
        Banner banner = (Banner) v.findViewById(R.id.rv_header);
        banner.setTopEntities(bean.getTopStories());
        adapter.setHeadView(v);
    }
}
