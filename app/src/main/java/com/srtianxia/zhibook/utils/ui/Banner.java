package com.srtianxia.zhibook.utils.ui;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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
public class Banner extends FrameLayout {
    private ViewPager viewPager;
    private LinearLayout linearLayoutDot;
    private List<ImageView> iv_dots;
    private Context context;
    private List<BannerData> bannerData;
    private List<SimpleDraweeView> bannerImg;
    private List<View> views;
    private int delayTime;
    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        views = new ArrayList<>();
        iv_dots = new ArrayList<>();
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
            views.add(fm);
        }

    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }
}
