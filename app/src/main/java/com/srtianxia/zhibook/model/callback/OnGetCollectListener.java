package com.srtianxia.zhibook.model.callback;

import com.srtianxia.zhibook.model.bean.zhibook.CollectBean;

/**
 * Created by srtianxia on 2016/2/15.
 */
public interface OnGetCollectListener {
    void success(CollectBean collectBean);
    void failure(String s);
}
