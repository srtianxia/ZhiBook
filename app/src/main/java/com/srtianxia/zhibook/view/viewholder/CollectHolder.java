package com.srtianxia.zhibook.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhibook.Collect;

/**
 * Created by srtianxia on 2016/2/15.
 */
public class CollectHolder extends RecyclerView.ViewHolder {
    private TextView tvTitle;
    private TextView tvItem01;
    private TextView tvItem02;
    private TextView tvItem03;
    public CollectHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_collect_title);
        tvItem01 = (TextView) itemView.findViewById(R.id.tv_collect_question_01);
        tvItem02 = (TextView) itemView.findViewById(R.id.tv_collect_question_02);
        tvItem03 = (TextView) itemView.findViewById(R.id.tv_collect_question_03);
    }

    public void setData(Collect collect,int position){
        tvTitle.setText(collect.getTitle());
        tvItem01.setText(collect.getItem01());
        tvItem02.setText(collect.getItem02());
        tvItem03.setText(collect.getItem03());
    }
}
