package com.srtianxia.zhibook.view.IView;

import com.srtianxia.zhibook.model.bean.zhibook.Question;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/11.
 */
public interface IFragmentQuestion {
    void showInitSuccess(List<Question> questions);
    void showInitFailure(String s);
}
