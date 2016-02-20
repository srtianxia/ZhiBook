package com.srtianxia.zhibook.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhihu.DailyBean;
import com.srtianxia.zhibook.presenter.DailyPresenter;
import com.srtianxia.zhibook.utils.ui.Banner;
import com.srtianxia.zhibook.utils.ui.DividerItemDecoration;
import com.srtianxia.zhibook.view.IView.IFragmentDaily;
import com.srtianxia.zhibook.view.activity.ActivityDailyContent;
import com.srtianxia.zhibook.view.adapter.DailyAdapter;
import com.srtianxia.zhibook.view.adapter.OnItemClickListener;
import com.srtianxia.zhibook.view.adapter.RefreshFootAdapter;

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
    private LinearLayoutManager manager;
    private int lastVisibleItem;
    //最新日报的日期
    private Integer initDate;

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
        initDate = Integer.valueOf(bean.getDate());
        adapter = new DailyAdapter(getActivity(),bean.getStories());
        rvDaily.setAdapter(adapter);
        manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvDaily.setLayoutManager(manager);
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
        rvDaily.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    adapter.changeFootStatus(RefreshFootAdapter.LOADING_MORE);
                    presenter.loadMore(String.valueOf(-- initDate));
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = manager.findLastVisibleItemPosition();
            }
        });
        rvDaily.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        View v = inflater.inflate(R.layout.header_banner,rvDaily,false);
        Banner banner = (Banner) v.findViewById(R.id.rv_header);
        banner.setTopEntities(bean.getTopStories());
        adapter.setHeadView(v);
    }

    @Override
    public void showLoadMoreSuccess(DailyBean bean) {
        adapter.addItems(bean.getStories());
        adapter.changeFootStatus(DailyAdapter.PULL_TO_MORE);
    }

    @Override
    public void showLoadMoreNoMore() {
        adapter.changeFootStatus(DailyAdapter.LOAD_NO_MORE);
    }
}
