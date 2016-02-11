package com.srtianxia.zhibook.view.IView;

import com.srtianxia.zhibook.model.bean.zhibook.Answer;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/11.
 */
public interface IActivityAnswer {
    String getQuestionId();

    void initAnswerSuccess(List<Answer> answers);
    void initAnswerFailure(String s);
}
