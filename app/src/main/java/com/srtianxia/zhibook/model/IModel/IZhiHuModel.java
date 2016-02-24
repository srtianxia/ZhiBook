package com.srtianxia.zhibook.model.Imodel;

import com.srtianxia.zhibook.model.callback.OnGetDailyContentListener;
import com.srtianxia.zhibook.model.callback.OnGetDailyListener;
import com.srtianxia.zhibook.model.callback.OnGetThemeListener;

/**
 * Created by srtianxia on 2016/1/23.
 */
public interface IZhiHuModel {
    void getDaily(OnGetDailyListener listener);
    void getDailyContent(String id,OnGetDailyContentListener listener);

    /***
     * 根据日期获得往日的列表
     * @param data
     * @param listener
     */
    void loadMore(String data,OnGetDailyListener listener);

    void getThemeList(OnGetThemeListener listener);
}
