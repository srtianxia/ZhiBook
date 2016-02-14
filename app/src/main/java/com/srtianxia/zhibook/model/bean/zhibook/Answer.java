package com.srtianxia.zhibook.model.bean.zhibook;

import java.io.Serializable;

/**
 * Created by srtianxia on 2016/2/8.
 */
public class Answer implements Serializable {
    private int id;
    private String data;
    private String answerAuthorName;
    private String content;
    private String answerAuthorHead;
    private int praise;

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswerAuthorHead() {
        return answerAuthorHead;
    }

    public void setAnswerAuthorHead(String answerAuthorHead) {
        this.answerAuthorHead = answerAuthorHead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAnswerAuthorName() {
        return answerAuthorName;
    }

    public void setAnswerAuthorName(String answerAuthorName) {
        this.answerAuthorName = answerAuthorName;
    }
}
