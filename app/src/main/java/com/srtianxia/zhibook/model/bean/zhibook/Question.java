package com.srtianxia.zhibook.model.bean.zhibook;

/**
 * Created by srtianxia on 2016/2/6.
 */
public class Question {
    private String title;
    private String content;
    private String authorHead;
    private String authorName;
    private String bestAnswerId;
    private String data;
    private int answerCount;
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorHead() {
        return authorHead;
    }

    public void setAuthorHead(String authorHead) {
        this.authorHead = authorHead;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBestAnswerId() {
        return bestAnswerId;
    }

    public void setBestAnswerId(String bestAnswerId) {
        this.bestAnswerId = bestAnswerId;
    }

    public String getDate() {
        return data;
    }

    public void setDate(String date) {
        this.data = date;
    }
}
