package com.srtianxia.zhibook.view.IView;

import com.srtianxia.zhibook.model.bean.zhihu.DailyContent;

/**
 * Created by srtianxia on 2016/2/12.
 */
public interface IActivityDailyContent {
    String getId();

    void showContentSuccess(DailyContent dailyContent);
}
