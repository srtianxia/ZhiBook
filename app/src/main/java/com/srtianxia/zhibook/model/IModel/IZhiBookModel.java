package com.srtianxia.zhibook.model.Imodel;

import com.srtianxia.zhibook.model.callback.OnGetQuestionListener;

/**
 * Created by srtianxia on 2016/2/11.
 */
public interface IZhiBookModel {
    void setQuestion(String title,String content,String token);
    void getQuestion(OnGetQuestionListener listener);
}
