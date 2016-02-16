package com.srtianxia.zhibook.model.callback;

import com.srtianxia.zhibook.model.bean.zhibook.CollectFolderBean;

/**
 * Created by srtianxia on 2016/2/15.
 */
public interface OnGetCollectListener {
    void success(CollectFolderBean bean);
    void failure(String s);
}
