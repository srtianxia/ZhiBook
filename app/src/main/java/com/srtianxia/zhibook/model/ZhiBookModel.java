package com.srtianxia.zhibook.model;

import android.util.Log;

import com.srtianxia.zhibook.model.Imodel.IZhiBookModel;
import com.srtianxia.zhibook.model.bean.zhibook.QuestionHolder;
import com.srtianxia.zhibook.model.callback.OnGetQuestionListener;
import com.srtianxia.zhibook.utils.http.RetrofitAPI;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by srtianxia on 2016/2/11.
 */
public class ZhiBookModel implements IZhiBookModel {
    private static final String TAG = "ZhiBookModel";
    private static ZhiBookModel zhiBookModel = new ZhiBookModel();
    private Retrofit retrofit;
    private RetrofitAPI retrofitAPI;

    private ZhiBookModel(){
        retrofit = new Retrofit.Builder().
                baseUrl(RetrofitAPI.BASIC_URL).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }

    public static ZhiBookModel getInstance(){
        return zhiBookModel;
    }

    @Override
    public void setQuestion(String title, String content, String token) {

    }

    @Override
    public void getQuestion(OnGetQuestionListener listener) {
        retrofitAPI.getQuestion().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<QuestionHolder>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.getMessage());
                    }

                    @Override
                    public void onNext(QuestionHolder questionHolder) {
                        Log.d(TAG,"size : " + questionHolder.getQuestions().size());
                    }
                });
    }
}
