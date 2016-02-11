package com.srtianxia.zhibook.model.callback;

import com.srtianxia.zhibook.model.bean.zhihu.DailyBean;

/**
 * Created by srtianxia on 2016/1/23.
 */
public interface OnGetDailyListener {
    void onGetDailySuccess(DailyBean bean);
}
