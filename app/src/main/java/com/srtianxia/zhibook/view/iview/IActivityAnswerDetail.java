package com.srtianxia.zhibook.view.IView;

import com.srtianxia.zhibook.model.bean.zhibook.CollectFolder;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/15.
 */
public interface IActivityAnswerDetail {
    void showAddCollectSuccess();

    void showAddCollectFailure();

    void showAddPraise();

    void showReducePraise();

    void showPraiseFailure(String s);

    void showFolders(List<CollectFolder> folders);
}
