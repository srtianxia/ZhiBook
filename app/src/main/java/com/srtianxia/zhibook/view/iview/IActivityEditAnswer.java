package com.srtianxia.zhibook.view.IView;

import android.net.Uri;

/**
 * Created by srtianxia on 2016/2/23.
 */
public interface IActivityEditAnswer {
    String getAcontent();
    String getQuestionId();

    void showEditSuccess();

    void upLoadPicAfter(String url, Uri uri);
}
