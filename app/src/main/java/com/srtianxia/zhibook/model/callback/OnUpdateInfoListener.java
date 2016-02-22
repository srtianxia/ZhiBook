package com.srtianxia.zhibook.model.callback;

import com.srtianxia.zhibook.model.bean.zhibook.User;

/**
 * Created by srtianxia on 2016/2/22.
 */
public interface OnUpdateInfoListener {
    void success(User user);
    void failure();
}
