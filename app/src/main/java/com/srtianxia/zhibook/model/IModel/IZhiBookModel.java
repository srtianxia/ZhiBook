package com.srtianxia.zhibook.model.Imodel;

import com.srtianxia.zhibook.model.callback.OnCollectListener;
import com.srtianxia.zhibook.model.callback.OnGetAnswerListener;
import com.srtianxia.zhibook.model.callback.OnGetCollectListener;
import com.srtianxia.zhibook.model.callback.OnGetQuestionListener;
import com.srtianxia.zhibook.model.callback.OnPraiseListener;

/**
 * Created by srtianxia on 2016/2/11.
 */
public interface IZhiBookModel {
    void setQuestion(String title,String content,String token);
    void getQuestion(OnGetQuestionListener listener);

    void setAnswer();
    void getAnswer(String questionId, OnGetAnswerListener listener);

    void praise(int i, OnPraiseListener listener);

    void getCollectionFolder(String token,OnGetCollectListener listener);
    void addCollectionFolder(String token,String folder,OnCollectListener listener);
    void setCollect(String token,int answerId,OnCollectListener listener);
    void cancelCollect(String token,int answerId,OnCollectListener listener);
}
