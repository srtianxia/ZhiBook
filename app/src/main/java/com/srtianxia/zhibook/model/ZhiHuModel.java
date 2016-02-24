package com.srtianxia.zhibook.model;

import android.util.Log;

import com.srtianxia.zhibook.app.APP;
import com.srtianxia.zhibook.model.Imodel.IZhiHuModel;
import com.srtianxia.zhibook.model.bean.zhihu.DailyBean;
import com.srtianxia.zhibook.model.bean.zhihu.DailyContent;
import com.srtianxia.zhibook.model.bean.zhihu.ThemeDailyBean;
import com.srtianxia.zhibook.model.callback.OnGetDailyContentListener;
import com.srtianxia.zhibook.model.callback.OnGetDailyListener;
import com.srtianxia.zhibook.model.callback.OnGetThemeListener;
import com.srtianxia.zhibook.utils.http.HttpUtils;
import com.srtianxia.zhibook.utils.http.RetrofitAPI;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
    private OkHttpClient client;

    private static ZhiHuModel zhiHuModel = new ZhiHuModel();

    public static ZhiHuModel getInstance(){
        return zhiHuModel;
    }

    private ZhiHuModel(){
        File cacheFile = new File(APP.getContext().getExternalCacheDir(),"ZhiBookCache");
        Cache cache = new Cache(cacheFile,1024*1024*50);
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!HttpUtils.isNetworkConnected(APP.getContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (HttpUtils.isNetworkConnected(APP.getContext())) {
                    int maxAge = 0 * 60;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        client = new OkHttpClient.Builder().cache(cache)
                .addInterceptor(interceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitAPI.BASIC_DAILY)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
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

    @Override
    public void loadMore(String data, final OnGetDailyListener listener) {
        retrofitAPI.getBefore(data).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.LoadNoMore();
                    }

                    @Override
                    public void onNext(DailyBean bean) {
                        listener.onGetDailySuccess(bean);
                    }
                });
    }

    @Override
    public void getThemeList(final OnGetThemeListener listener) {
        retrofitAPI.getTheme().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ThemeDailyBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.getMessage());
                        listener.failure(e.getMessage());
                    }

                    @Override
                    public void onNext(ThemeDailyBean bean) {
                        listener.success(bean);
                    }
                });
    }
}
