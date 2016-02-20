package com.srtianxia.zhibook.presenter;

import com.srtianxia.zhibook.model.Imodel.IZhiBookModel;
import com.srtianxia.zhibook.model.ZhiBookModel;
import com.srtianxia.zhibook.model.bean.zhibook.Answer;
import com.srtianxia.zhibook.model.callback.OnGetAnswerListener;
import com.srtianxia.zhibook.view.IView.IActivityAnswer;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/11.
 */
public class GetAnswerPresenter {
    private IActivityAnswer iActivityAnswer;
    private IZhiBookModel iZhiBookModel;

    public GetAnswerPresenter(IActivityAnswer iActivityAnswer){
        this.iActivityAnswer = iActivityAnswer;
        iZhiBookModel = ZhiBookModel.getInstance();
    }

    public void getAnswer(){
        iZhiBookModel.getAnswer(iActivityAnswer.getQuestionId(), new OnGetAnswerListener() {
            @Override
            public void success(List<Answer> answers) {
                iActivityAnswer.initAnswerSuccess(answers);
            }

            @Override
            public void failure(String s) {
                iActivityAnswer.initAnswerFailure(s);
            }
        });
    }

    public void onRelieveView() {
        if (iActivityAnswer != null) iActivityAnswer = null;
    }
}
