package com.srtianxia.zhibook.utils.http;

import com.srtianxia.zhibook.model.bean.zhibook.Question;
import com.srtianxia.zhibook.model.bean.zhibook.QuestionHolder;
import com.srtianxia.zhibook.model.bean.zhibook.User;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;


/**
 * Created by srtianxia on 2016/2/10.
 */
public interface RetrofitAPI {
    String BASIC_URL = "http://115.28.64.168/zhishu/";

    @FormUrlEncoded
    @POST("login.php")
    Observable<User> getLoginUser(
            @Field("name") String name,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Observable<User> getRegisterUser(
            @Field("name") String name,
            @Field("password") String password);


    @GET("getQuestion.php")
    Observable<QuestionHolder> getQuestion();

    @FormUrlEncoded
    @POST("setQuestion.php")
    Observable<Object> setQuestion(
            @Field("title") String title,
            @Field("content") String content,
            @Field("token") String toke);
}
