package com.srtianxia.zhibook.app;

/**
 * Created by srtianxia on 2016/1/22.
 */
public class API {
    public static int httpCodeOk = 200;
    //日报API部分
    public static String latestDailyUrl = "http://news-at.zhihu.com/api/4/news/latest";

    //阿里云后端部分
    public static String register = "http://115.28.64.168/zhishu/register.php";
    public static String login = "http://115.28.64.168/zhishu/login.php";
    public static String setQuestion = "http://115.28.64.168/zhishu/setQuestion.php";
    public static String getQuestion = "http://115.28.64.168/zhishu/getQuestion.php";
    public static String setAnswer = "http://115.28.64.168/zhishu/setAnswer.php";
    public static String getAnswer = "http://115.28.64.168/zhishu/getAnswer.php";
    public static String getCollectFolder = "http://115.28.64.168/zhishu/getCollectFolder.php";
    public static String addCollectFolder = "http://115.28.64.168/zhishu/addCollectFolder.php";
    public static String updatePersonInfo = "http://115.28.64.168/zhishu/updatePersonInfo.php";
    public static String addPraise = "http://115.28.64.168/zhishu/addPraise.php";

}
