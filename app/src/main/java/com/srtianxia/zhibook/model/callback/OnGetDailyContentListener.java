package com.srtianxia.zhibook.model.callback;

import com.srtianxia.zhibook.model.bean.zhihu.DailyContent;

/**
 * Created by srtianxia on 2016/2/12.
 */
public interface OnGetDailyContentListener {
    void success(DailyContent content);
    void failure();
}
