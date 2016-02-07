package com.srtianxia.zhibook.model;

import android.util.Log;

import com.google.gson.Gson;
import com.srtianxia.zhibook.app.API;
import com.srtianxia.zhibook.model.Imodel.IZhiHuModel;
import com.srtianxia.zhibook.model.bean.zhihu.DailyBean;
import com.srtianxia.zhibook.model.callback.OnGetDailyListener;
import com.srtianxia.zhibook.utils.http.AsyNetUtils;
import com.srtianxia.zhibook.utils.http.OkHttpUtils;
import com.srtianxia.zhibook.utils.http.callback.NetUtilsCallback;
import com.srtianxia.zhibook.utils.http.callback.OkHttpUtilsCallback;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by srtianxia on 2016/1/23.
 */
public class ZhiHuModel implements IZhiHuModel{
    private static final String TAG = "ZhiHuModel";

    private static ZhiHuModel zhiHuModel = new ZhiHuModel();

    public static ZhiHuModel getInstance(){
        return zhiHuModel;
    }

    private ZhiHuModel(){}


    @Override
    public void getDaily(final OnGetDailyListener listener) {
        AsyNetUtils.getRequest(API.latestDailyUrl, new NetUtilsCallback() {
            @Override
            public void onResponse(String response) {
//                Gson gson = new Gson();
//                DailyBean dailyBean = gson.fromJson(response,DailyBean.class);
//                listener.onGetDaily(dailyBean);
                Log.d(TAG, response);
            }
        });

    }
}
