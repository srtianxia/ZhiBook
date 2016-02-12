package com.srtianxia.zhibook.utils.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhihu.BannerData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srtianxia on 2016/2/11.
 */

public class Banner extends FrameLayout implements View.OnClickListener {
    private ViewPager viewPager;
    private LinearLayout linearLayoutDot;
    private List<ImageView> iv_dots;
    private Context context;
    private List<BannerData> bannerData;
    private List<SimpleDraweeView> bannerImg;
    private List<View> views;
    private int delayTime;
    private boolean isAutoPlay;
    private int currentItem;
    private Handler handler = new Handler();
    private OnItemClickListener mItemClickListener;

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.bannerData = new ArrayList<>();
        views = new ArrayList<>();
        iv_dots = new ArrayList<>();
        delayTime = 2000;
        initView();
    }

    public Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context) {
        this(context, null);
    }

    public void setBannerData(List<BannerData> bannerData) {
        this.bannerData = bannerData;
        views.clear();
        initView();
    }

    private void initView() {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.ui_banner_layout,this,true);
        viewPager = (ViewPager) v.findViewById(R.id.banner_viewpager);
        linearLayoutDot = (LinearLayout) v.findViewById(R.id.banner_linearLayout_dot);
        linearLayoutDot.removeAllViews();
        int length = bannerData.size();
        for (int i = 0;i<length;i++){
            ImageView iv_dot = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            linearLayoutDot.addView(iv_dot, params);
            iv_dots.add(iv_dot);
        }

        for (int i = 0;i<=length+1;i++){
            View fm = LayoutInflater.from(context)
                    .inflate(R.layout.ui_banner_content,null);
            SimpleDraweeView img = (SimpleDraweeView) fm.findViewById(R.id.banner_img);
            TextView tv = (TextView) fm.findViewById(R.id.banner_title);
            if (i==0){
                img.setImageURI(Uri.parse(bannerData.get(length-1).getImage()));
                tv.setText(bannerData.get(length-1).getTitle());
            }else if (i == length + 1) {
                img.setImageURI(Uri.parse(bannerData.get(0).getImage()));
                tv.setText(bannerData.get(0).getTitle());
            } else {
                img.setImageURI(Uri.parse(bannerData.get(i-1).getImage()));
                tv.setText(bannerData.get(i-1).getTitle());
            }
            fm.setOnClickListener(this);
            views.add(fm);
        }
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setFocusable(true);
        viewPager.setCurrentItem(1);
        currentItem = 1;
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        startPlay();
    }

    private void startPlay() {
        isAutoPlay = true;
        handler.postDelayed(task, 3000);
    }


    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    private final Runnable task = new Runnable() {

        @Override
        public void run() {
            if (isAutoPlay) {
                currentItem = currentItem % (bannerData.size() + 1) + 1;
                if (currentItem == 1) {
                    viewPager.setCurrentItem(currentItem, false);
                    handler.post(task);
                } else {
                    viewPager.setCurrentItem(currentItem);
                    handler.postDelayed(task, 5000);
                }
            } else {
                handler.postDelayed(task, 5000);
            }
        }
    };




    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case 1:
                    isAutoPlay = false;
                    break;
                case 2:
                    isAutoPlay = true;
                    break;
                case 0:
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(bannerData.size(), false);
                    } else if (viewPager.getCurrentItem() == bannerData.size() + 1) {
                        viewPager.setCurrentItem(1, false);
                    }
                    currentItem = viewPager.getCurrentItem();
                    isAutoPlay = true;
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < iv_dots.size(); i++) {
                if (i == arg0 - 1) {
                    iv_dots.get(i).setImageResource(R.drawable.dot_focus);
                } else {
                    iv_dots.get(i).setImageResource(R.drawable.dot_blur);
                }
            }
        }

    }


    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        public void click(View v, BannerData entity);
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            BannerData data = bannerData.get(viewPager.getCurrentItem() - 1);
            mItemClickListener.click(v, data);
        }
    }
}
