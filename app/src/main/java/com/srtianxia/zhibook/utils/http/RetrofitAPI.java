package com.srtianxia.zhibook.utils.http;

import com.srtianxia.zhibook.model.bean.ChatBean;
import com.srtianxia.zhibook.model.bean.zhibook.AnswerBean;
import com.srtianxia.zhibook.model.bean.zhibook.CollectFolderBean;
import com.srtianxia.zhibook.model.bean.zhibook.EssayBean;
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
    @POST("getCollectFolder.php")
    Observable<CollectFolderBean> getCollection(
            @Field("token") String token);

    @FormUrlEncoded
    @POST("addCollectFolder.php")
    Observable<String> addFolder(
            @Field("token") String token,
            @Field("folder") String folder);

    @FormUrlEncoded
    @POST("setEssay.php")
    Observable<EssayBean> setEssay(
            @Field("title") String title,
            @Field("content") String content,
            @Field("authorId") String authorId,
            @Field("isPrivate") String isPrivate);


    //日报部分
    @GET("news/latest")
    Observable<DailyBean> getDaily();

    /**
     * 日报详情部分
     * @param id
     * @return
     */
    @GET("news/{id}")
    Observable<DailyContent> getDailyContent(@Path("id") String id);

    /**
     * 上拉加载更多的时候传入data（当前的data - 1）
     * @param data
     * @return
     */
    @GET("news/before/{data}")
    Observable<DailyBean> getBefore(@Path("data") String data);

    //聊天机器人部分
    @FormUrlEncoded
    @POST("http://www.tuling123.com/openapi/api")
    Observable<ChatBean> getMsg(
            @Field("key") String apiKey,
            @Field("info") String sendMsg);
}
