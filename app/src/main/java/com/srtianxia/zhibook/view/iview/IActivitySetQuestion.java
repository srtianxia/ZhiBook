package com.srtianxia.zhibook.view.IView;

import android.net.Uri;

/**
 * Created by srtianxia on 2016/2/23.
 */
public interface IActivitySetQuestion {
    String getQTitle();
    String getQContent();

    void setQuestionSuccess();
    void setQuestionFailure();

    void uploadPicAfter(String url, Uri uri);
}
