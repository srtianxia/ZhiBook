package com.srtianxia.zhibook.presenter;

import com.srtianxia.zhibook.model.Imodel.IZhiBookModel;
import com.srtianxia.zhibook.model.ZhiBookModel;
import com.srtianxia.zhibook.model.callback.OnSetAnswerListener;
import com.srtianxia.zhibook.view.IView.IActivityEditAnswer;

/**
 * Created by srtianxia on 2016/2/23.
 */
public class ActivityEditAnswerPresenter {
    private IActivityEditAnswer iActivityEditAnswer;
    private IZhiBookModel iZhiBookModel;

    public ActivityEditAnswerPresenter(IActivityEditAnswer iActivityEditAnswer){
        this.iActivityEditAnswer = iActivityEditAnswer;
        iZhiBookModel = ZhiBookModel.getInstance();
    }

    public void editAnswer(){
        iZhiBookModel.setAnswer(iActivityEditAnswer.getAcontent(),
                iActivityEditAnswer.getQuestionId(), "token",
                new OnSetAnswerListener() {
                    @Override
                    public void success() {
                        iActivityEditAnswer.showEditSuccess();
                    }

                    @Override
                    public void failure() {

                    }
                });
    }
}
