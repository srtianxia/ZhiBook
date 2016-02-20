package com.srtianxia.zhibook.presenter;

import android.util.Log;

import com.srtianxia.zhibook.model.Imodel.IZhiBookModel;
import com.srtianxia.zhibook.model.ZhiBookModel;
import com.srtianxia.zhibook.model.bean.zhibook.CollectFolderBean;
import com.srtianxia.zhibook.model.callback.OnGetCollectListener;
import com.srtianxia.zhibook.model.callback.OnPraiseListener;
import com.srtianxia.zhibook.view.IView.IActivityAnswerDetail;

/**
 * Created by srtianxia on 2016/2/16.
 */
public class AnswerDetailPresenter {
    private IZhiBookModel iZhiBookModel;
    private IActivityAnswerDetail iActivityAnswerDetail;

    public AnswerDetailPresenter(IActivityAnswerDetail iActivityAnswerDetail){
        this.iActivityAnswerDetail = iActivityAnswerDetail;
        iZhiBookModel = ZhiBookModel.getInstance();
    }

    public void praise(int i){
        iZhiBookModel.praise(i, new OnPraiseListener() {
            @Override
            public void add() {
                iActivityAnswerDetail.showAddPraise();
            }

            @Override
            public void reduce() {
                iActivityAnswerDetail.showReducePraise();
            }

            @Override
            public void failure(String s) {
                iActivityAnswerDetail.showPraiseFailure(s);
            }
        });
    }

    public void getFolder(){
        iZhiBookModel.getCollectionFolder("20fbfa0105efad5d789427f50c2d112688400478", new OnGetCollectListener() {
            @Override
            public void success(CollectFolderBean bean) {
                iActivityAnswerDetail.showFolders(bean.getFolders());
            }

            @Override
            public void failure(String s) {

            }
        });
    }

    public void onRelieveView() {
        if (iActivityAnswerDetail != null) iActivityAnswerDetail = null;
    }
}
