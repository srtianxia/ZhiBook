package com.srtianxia.zhibook.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.view.fragment.FragmentDaily;
import com.srtianxia.zhibook.view.fragment.FragmentQuestion;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/12.
 */
public class ActivityFind extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tab_layout_appbar)
    TabLayout tabLayoutAppbar;
    @Bind(R.id.vp_find)
    ViewPager vpFind;

    private FragmentDaily fragmentDaily;
    private FragmentQuestion fragmentQuestion;
    private HomePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        pagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
        vpFind.setOffscreenPageLimit(2);
        vpFind.setAdapter(pagerAdapter);
        tabLayoutAppbar.setTabTextColors(getResources().getColor(R.color.colorWhite), getResources().getColor(R.color.tab_rule_yellow));
        tabLayoutAppbar.setupWithViewPager(vpFind);
        tabLayoutAppbar.setTabsFromPagerAdapter(pagerAdapter);
        toolbar.setTitle(getResources().getString(R.string.tab_find));
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public class HomePagerAdapter extends FragmentPagerAdapter {
        private final String[] titles = {"日报","收藏"};

        public HomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    if (fragmentDaily == null){
                        fragmentDaily = new FragmentDaily();
                    }
                    return fragmentDaily;
                case 1:
                    if (fragmentQuestion == null){
                        fragmentQuestion = new FragmentQuestion();
                    }
                    return fragmentQuestion;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
}
