package com.srtianxia.zhibook.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.view.adapter.RefreshFootAdapter;
import com.srtianxia.zhibook.view.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/12.
 */

public class FragmentTest extends Fragment {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_test)
    RecyclerView rvTest;
    private View view;

    private TestAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private int lastVisibleItem;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this, view);
        List<String> items = new ArrayList<>();
        for (int i = 0;i<10;i++){
            items.add("第"+i+"个item");
        }
        adapter = new TestAdapter(getActivity(),items);
        rvTest.setAdapter(adapter);
        rvTest.setLayoutManager(linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rvTest.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    adapter.changeFootStatus(RefreshFootAdapter.LOADING_MORE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<String> newDatas = new ArrayList<String>();
                            for (int i = 0; i < 5; i++) {
                                int index = i + 1;
                                newDatas.add("more item" + index);
                            }
                            adapter.addMoreItem(newDatas);
                            adapter.changeFootStatus(RefreshFootAdapter.PULLUP_LOAD_MORE);
                        }
                    }, 2500);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
        View v = inflater.inflate(R.layout.test_head,rvTest,false);

        adapter.setHeadView(v);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
