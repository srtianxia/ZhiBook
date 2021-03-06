package com.srtianxia.zhibook.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhibook.Answer;
import com.srtianxia.zhibook.utils.ui.CircleImageView;

/**
 * Created by srtianxia on 2016/2/8.
 */
public class AnswerHolder extends RecyclerView.ViewHolder {
    private TextView authorName;
    private TextView answerContent;
    private CircleImageView authorHead;
    private TextView praiseCount;
    public AnswerHolder(View itemView) {
        super(itemView);
        authorName = (TextView) itemView.findViewById(R.id.tv_answer_authorName);
        answerContent = (TextView) itemView.findViewById(R.id.tv_answer_content);
        authorHead = (CircleImageView) itemView.findViewById(R.id.img_answer_authorHead);
        praiseCount = (TextView) itemView.findViewById(R.id.tv_praiseCount);
    }

    public void bindData(Answer answer,int position){
        authorName.setText(answer.getAnswerAuthorName());
        answerContent.setText(answer.getContent());
        Glide.with(itemView.getContext())
                .load(answer.getAnswerAuthorHead())
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .crossFade()
                .into(authorHead);
        praiseCount.setText(""+answer.getPraise());
    }
}
