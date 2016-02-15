package com.srtianxia.zhibook.utils.http;

import com.srtianxia.zhibook.model.bean.zhibook.AnswerBean;
import com.srtianxia.zhibook.model.bean.zhibook.CollectBean;
import com.srtianxia.zhibook.model.bean.zhibook.QuestionBean;
import com.srtianxia.zhibook.model.bean.zhibook.User;
import com.srtianxia.zhibook.model.bean.zhihu.DailyBean;
import com.srtianxia.zhibook.model.bean.zhihu.DailyContent;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;


/**
 * Created by srtianxia on 2016/2/10.
 */
public interface RetrofitAPI {
    String BASIC_URL = "http://115.28.64.168/zhishu/";
    String BASIC_DAILY = "http://news-at.zhihu.com/api/4/";

    //ZhiBook部分
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
    Observable<QuestionBean> getQuestion();

    @FormUrlEncoded
    @POST("setQuestion.php")
    Observable<Object> setQuestion(
            @Field("title") String title,
            @Field("content") String content,
            @Field("token") String toke);

    @FormUrlEncoded
    @POST("getAnswer.php")
    Observable<AnswerBean> getAnswer(
      @Field("questionId") String id);

    @FormUrlEncoded
    @POST("setAnswer.php")
    Observable<Object> setAnswer(
        @Field("questionId") String id,
        @Field("content") String content,
        @Field("token") String token);

    @FormUrlEncoded
    @GET("getCollection.php")
    Observable<CollectBean> getCollection();

    //日报部分
    @GET("news/latest")
    Observable<DailyBean> getDaily();

    @GET("news/{id}")
    Observable<DailyContent> getDailyContent(@Path("id") String id);
}
