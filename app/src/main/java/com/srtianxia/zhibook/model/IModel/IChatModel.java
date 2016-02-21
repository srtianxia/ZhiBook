package com.srtianxia.zhibook.model.Imodel;

import com.srtianxia.zhibook.model.callback.OnChatListener;

/**
 * Created by srtianxia on 2016/2/20.
 */
public interface IChatModel {
    void postMsg(String msg, String apiKey, OnChatListener listener);
}
