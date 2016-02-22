package com.srtianxia.zhibook.presenter;

import android.net.Uri;

import com.srtianxia.zhibook.model.FileModel;
import com.srtianxia.zhibook.model.Imodel.IFileModel;
import com.srtianxia.zhibook.model.Imodel.IZhiBookModel;
import com.srtianxia.zhibook.model.ZhiBookModel;
import com.srtianxia.zhibook.model.callback.OnSaveListener;
import com.srtianxia.zhibook.model.callback.OnUploadListener;
import com.srtianxia.zhibook.view.IView.IActivityHome;

/**
 * Created by srtianxia on 2016/2/22.
 */
public class HomePresenter {
    private IActivityHome iActivityHome;
    private IZhiBookModel iZhiBookModel;

    public HomePresenter(IActivityHome iActivityHome){
        this.iActivityHome = iActivityHome;
        iZhiBookModel = ZhiBookModel.getInstance();
    }

    public void upLoadHead(Uri uri){
        iZhiBookModel.upLoadHead(uri, "20fbfa0105efad5d789427f50c2d112688400478", new OnUploadListener() {
            @Override
            public void success() {

            }

            @Override
            public void progress(int progress) {

            }

            @Override
            public void failure() {

            }
        });
    }
}
