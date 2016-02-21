package com.srtianxia.zhibook.model.bean;

/**
 * Created by srtianxia on 2016/2/20.
 */
public class ChatBean {
    public static final int TYPE_RIGHT = 0;
    public static final int TYPE_LEFT = 1;

    private String content;
    private String headUrl;
    private int type;

    public ChatBean(String content,String headUrl,int type){
        this.content = content;
        this.headUrl = headUrl;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
