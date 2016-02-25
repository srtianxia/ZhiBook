package com.srtianxia.zhibook.view.IView;

/**
 * Created by srtianxia on 2016/2/23.
 */
public interface IActivitySetQuestion {
    String getQTitle();
    String getQContent();

    void setQuestionSuccess();
    void setQuestionFailure();

    void uploadPicAfter(String url);
}
