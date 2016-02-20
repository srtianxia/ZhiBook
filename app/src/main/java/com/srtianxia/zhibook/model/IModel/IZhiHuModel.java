package com.srtianxia.zhibook.model.Imodel;

import com.srtianxia.zhibook.model.callback.OnGetDailyContentListener;
import com.srtianxia.zhibook.model.callback.OnGetDailyListener;

/**
 * Created by srtianxia on 2016/1/23.
 */
public interface IZhiHuModel {
    void getDaily(OnGetDailyListener listener);
    void getDailyContent(String id,OnGetDailyContentListener listener);

    void loadMore(String data,OnGetDailyListener listener);
}
