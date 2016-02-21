package com.srtianxia.zhibook.model.bean;

/**
 * Created by srtianxia on 2016/2/20.
 */
public class ChatBean {
    public static final int TYPE_RIGHT = 0;
    public static final int TYPE_LEFT = 1;

    private String text;
    private String headUrl;
    private int code;
    private int type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
