package com.srtianxia.zhibook.presenter;

import android.util.Log;

import com.srtianxia.zhibook.model.Imodel.IZhiBookModel;
import com.srtianxia.zhibook.model.ZhiBookModel;
import com.srtianxia.zhibook.model.bean.zhibook.CollectFolderBean;
import com.srtianxia.zhibook.model.callback.OnCollectListener;
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

    public void getCollectionFolder(){
        iZhiBookModel.getCollectionFolder("20fbfa0105efad5d789427f50c2d112688400478", new OnGetCollectListener() {
            @Override
            public void success(CollectFolderBean bean) {
                iFragmentCollect.showCollection(bean);
            }

            @Override
            public void failure(String s) {

            }
        });
    }

    public void addFolder(String token,String folder){
        Log.d("234",folder);
        iZhiBookModel.addCollectionFolder("20fbfa0105efad5d789427f50c2d112688400478", folder, new OnCollectListener() {
            @Override
            public void success() {

            }
        });
    }
}
