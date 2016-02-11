package com.srtianxia.zhibook.presenter;

import com.srtianxia.zhibook.model.Imodel.IZhiBookModel;
import com.srtianxia.zhibook.model.ZhiBookModel;
import com.srtianxia.zhibook.model.bean.zhibook.Question;
import com.srtianxia.zhibook.model.callback.OnGetQuestionListener;
import com.srtianxia.zhibook.view.IView.IFragmentQuestion;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/11.
 */
public class GetQuestionPresenter {
    private IFragmentQuestion iFragmentQuestion;
    private IZhiBookModel iZhiBook;

    public GetQuestionPresenter(IFragmentQuestion iFragmentQuestion){
        this.iFragmentQuestion = iFragmentQuestion;
        this.iZhiBook = ZhiBookModel.getInstance();
    }

    public void getQuestion(){
        iZhiBook.getQuestion(new OnGetQuestionListener() {
            @Override
            public void success(List<Question> questions) {
                iFragmentQuestion.showInitSuccess(questions);
            }

            @Override
            public void failure(String s) {

            }
        });
    }
}
