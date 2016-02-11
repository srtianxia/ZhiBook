package com.srtianxia.zhibook.model.callback;

import com.srtianxia.zhibook.model.bean.zhibook.Answer;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/11.
 */
public interface OnGetAnswerListener {
    void success(List<Answer> answers);
    void failure(String s);
}
