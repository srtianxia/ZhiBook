package com.srtianxia.zhibook.presenter;

import com.srtianxia.zhibook.model.Imodel.IZhiBookModel;
import com.srtianxia.zhibook.model.ZhiBookModel;
import com.srtianxia.zhibook.model.callback.OnSetQuestionListener;
import com.srtianxia.zhibook.view.IView.IActivitySetQuestion;

/**
 * Created by srtianxia on 2016/2/23.
 */
public class SetQuestionPresenter {
    private IActivitySetQuestion iActivitySetQuestion;
    private IZhiBookModel iZhiBookModel;

    public SetQuestionPresenter(IActivitySetQuestion iActivitySetQuestion){
        this.iActivitySetQuestion = iActivitySetQuestion;
        iZhiBookModel = ZhiBookModel.getInstance();
    }

    public void setQuestion(){
        iZhiBookModel.setQuestion(iActivitySetQuestion.getQTitle(),
                iActivitySetQuestion.getQContent(),
                "token", new OnSetQuestionListener() {
                    @Override
                    public void success() {
                        iActivitySetQuestion.setQuestionSuccess();
                    }

                    @Override
                    public void failure() {
                        iActivitySetQuestion.setQuestionFailure();
                    }
                });
    }
}
