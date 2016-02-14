package com.srtianxia.zhibook.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhihu.BannerData;
import com.srtianxia.zhibook.utils.ui.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/12.
 */

public class FragmentTest extends Fragment {
    @Bind(R.id.kanner)
    Banner banner;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this, view);
//        List<BannerData> datas = new ArrayList<>();
//        datas.add(new BannerData("http://pic3.zhimg.com/112d19aee503dbb9ff8a7f48e0c4653e.jpg","年度热门 · 面试官问现在工资是多少该怎么回答他呢？"));
//        datas.add(new BannerData("http://pic3.zhimg.com/0e42ddcbe5401a614a081891072f093e.jpg","4342343223"));
//        datas.add(new BannerData("http://pic2.zhimg.com/23337493096e62113bd03f71db836bdd.jpg","43432423234"));
//        banner.setTopEntities(datas);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
