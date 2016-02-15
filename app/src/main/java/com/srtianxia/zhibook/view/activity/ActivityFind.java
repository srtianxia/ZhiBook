package com.srtianxia.zhibook.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.utils.ui.OnAnimationEndListener;
import com.srtianxia.zhibook.utils.ui.ScaleXYAnimation;
import com.srtianxia.zhibook.view.fragment.FragmentCollect;
import com.srtianxia.zhibook.view.fragment.FragmentDaily;

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
    @Bind(R.id.find_fab)
    FloatingActionButton findFab;

    private static final String TAG = "ActivityFind";
    private FragmentCollect fragmentCollect;
    private FragmentDaily fragmentDaily;
    private HomePagerAdapter pagerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        findFab.setVisibility(View.GONE);
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
        vpFind.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        ScaleXYAnimation.hide(findFab, new OnAnimationEndListener() {
                            @Override
                            public void onEnd() {
                                findFab.setVisibility(View.GONE);
                            }
                        });
                        break;
                    case 1:
                        findFab.setVisibility(View.VISIBLE);
                        ScaleXYAnimation.show(findFab, null);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public class HomePagerAdapter extends FragmentPagerAdapter {
        private final String[] titles = {"日报", "收藏"};

        public HomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (fragmentDaily == null) {
                        fragmentDaily = new FragmentDaily();
                    }
                    return fragmentDaily;
                case 1:
                    if (fragmentCollect == null) {
                        fragmentCollect = new FragmentCollect();
                    }
                    return fragmentCollect;
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
