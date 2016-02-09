package com.srtianxia.zhibook.view.viewholder;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhibook.Answer;

/**
 * Created by srtianxia on 2016/2/8.
 */
public class AnswerHolder extends RecyclerView.ViewHolder {
    private TextView authorName;
    private TextView answerContent;
    private SimpleDraweeView authorHead;
    private TextView praiseCount;
    public AnswerHolder(View itemView) {
        super(itemView);
        authorName = (TextView) itemView.findViewById(R.id.tv_answer_authorName);
        answerContent = (TextView) itemView.findViewById(R.id.tv_answer_content);
        authorHead = (SimpleDraweeView) itemView.findViewById(R.id.img_answer_authorHead);
        praiseCount = (TextView) itemView.findViewById(R.id.tv_praiseCount);
    }

    public void bindData(Answer answer,int position){
        authorName.setText(answer.getAnswerAuthorName());
        answerContent.setText(answer.getContent());
        authorHead.setImageURI(Uri.parse(answer.getAnswerAuthorHead()));
        praiseCount.setText(""+answer.getPraise());
    }
}
