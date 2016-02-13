package com.srtianxia.zhibook.model;

import android.util.Log;

import com.srtianxia.zhibook.model.Imodel.IZhiHuModel;
import com.srtianxia.zhibook.model.bean.zhihu.DailyBean;
import com.srtianxia.zhibook.model.bean.zhihu.DailyContent;
import com.srtianxia.zhibook.model.callback.OnGetDailyContentListener;
import com.srtianxia.zhibook.model.callback.OnGetDailyListener;
import com.srtianxia.zhibook.utils.http.RetrofitAPI;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by srtianxia on 2016/1/23.
 */
public class ZhiHuModel implements IZhiHuModel{
    private static final String TAG = "ZhiHuModel";
    private RetrofitAPI retrofitAPI;
    private Retrofit retrofit;

    private static ZhiHuModel zhiHuModel = new ZhiHuModel();

    public static ZhiHuModel getInstance(){
        return zhiHuModel;
    }

    private ZhiHuModel(){
        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitAPI.BASIC_DAILY)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }


    @Override
    public void getDaily(final OnGetDailyListener listener) {
        retrofitAPI.getDaily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.getMessage());
                    }

                    @Override
                    public void onNext(DailyBean bean) {
                        listener.onGetDailySuccess(bean);
                    }
                });
    }

    @Override
    public void getDailyContent(String id, final OnGetDailyContentListener listener) {
        retrofitAPI.getDailyContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyContent>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.getMessage());
                    }

                    @Override
                    public void onNext(DailyContent dailyContent) {
                        listener.success(dailyContent);
                    }
                });
    }
}
