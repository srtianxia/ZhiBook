package com.srtianxia.zhibook.model.callback;

/**
 * Created by srtianxia on 2016/2/16.
 */
public interface OnPraiseListener {
    void add();
    void reduce();
    void failure(String s);
}
