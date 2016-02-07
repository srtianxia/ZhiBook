package com.srtianxia.zhibook.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhibook.Question;
import com.srtianxia.zhibook.utils.ui.CircleImageView;

/**
 * Created by srtianxia on 2016/2/6.
 */
public class QuestionHolder extends RecyclerView.ViewHolder{
    private TextView tv_question_author;
    private TextView tv_question_title;
    private TextView tv_question_content;
    private TextView tv_question_answerCount;
    private CircleImageView img_question_author_head;

    public QuestionHolder(View itemView){
        super(itemView);
        tv_question_author = (TextView) itemView.findViewById(R.id.tv_question_author);
        tv_question_title = (TextView) itemView.findViewById(R.id.tv_question_title);
        tv_question_content = (TextView) itemView.findViewById(R.id.tv_question_content);
        tv_question_answerCount = (TextView) itemView.findViewById(R.id.tv_question_answerCount);
        img_question_author_head = (CircleImageView) itemView.findViewById(R.id.img_question_author_head);
    }

    public void bindData(Question question,int position){
        tv_question_author.setText(question.getAuthorName()+"提出的问题");
        tv_question_title.setText(question.getTitle());
        tv_question_content.setText(question.getContent());
        tv_question_answerCount.setText(""+question.getAnswerCount());
//        img_question_author_head.setImageBitmap(question.get);
    }
}
