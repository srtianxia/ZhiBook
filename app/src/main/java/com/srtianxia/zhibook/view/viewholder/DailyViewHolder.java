package com.srtianxia.zhibook.view.viewholder;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhihu.Story;

/**
 * Created by srtianxia on 2016/1/23.
 */
public class DailyViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "DailyViewHolder";
    private TextView tvDailyTitle;
    private SimpleDraweeView imgDaily;

    public DailyViewHolder(View itemView) {
        super(itemView);
        tvDailyTitle = (TextView) itemView.findViewById(R.id.tv_daily_title);
        imgDaily = (SimpleDraweeView) itemView.findViewById(R.id.img_daily_pic);
    }

    public void bindData(Story data, int position){
        tvDailyTitle.setText(data.getTitle());
        imgDaily.setImageURI(Uri.parse(data.getImages().get(0)));
    }
}
