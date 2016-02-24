package com.srtianxia.zhibook.model.callback;

import com.srtianxia.zhibook.model.bean.zhihu.ThemeDailyBean;

/**
 * Created by srtianxia on 2016/2/24.
 */
public interface OnGetThemeListener {
    void success(ThemeDailyBean bean);
    void failure(String s);
}
