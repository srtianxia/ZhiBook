package com.srtianxia.zhibook.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhihu.Story;

/**
 * Created by srtianxia on 2016/1/23.
 */
public class DailyViewHolder extends RecyclerView.ViewHolder {
    private TextView tvDailyTitle;
    public DailyViewHolder(View itemView) {
        super(itemView);
        tvDailyTitle = (TextView) itemView.findViewById(R.id.tv_daily_title);
    }

    public void bindData(Story data, int position){
        tvDailyTitle.setText(data.getTitle());
    }
}
