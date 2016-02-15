package com.srtianxia.zhibook.presenter;

import com.srtianxia.zhibook.model.Imodel.IZhiBookModel;
import com.srtianxia.zhibook.model.ZhiBookModel;
import com.srtianxia.zhibook.model.bean.zhibook.CollectBean;
import com.srtianxia.zhibook.model.callback.OnGetCollectListener;
import com.srtianxia.zhibook.view.IView.IFragmentCollect;

/**
 * Created by srtianxia on 2016/2/15.
 */
public class CollectPresenter {
    private IZhiBookModel iZhiBookModel;
    private IFragmentCollect iFragmentCollect;
    public CollectPresenter(IFragmentCollect iFragmentCollect){
        this.iFragmentCollect = iFragmentCollect;
        iZhiBookModel = ZhiBookModel.getInstance();
    }

    public void getCollection(){
        iZhiBookModel.getCollection(new OnGetCollectListener() {
            @Override
            public void success(CollectBean collectBean) {

            }

            @Override
            public void failure(String s) {

            }
        });
    }
}
