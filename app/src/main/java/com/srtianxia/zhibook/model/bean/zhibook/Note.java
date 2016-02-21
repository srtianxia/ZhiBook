package com.srtianxia.zhibook.model.bean.zhibook;

/**
 * Created by srtianxia on 2016/2/17.
 */
public class Note {
    private String content;
    private Integer id;
    private Integer authorId;

    public Note(String content,Integer id,Integer authorId){
        this.content = content;
        this.authorId = authorId;
        this.id = id;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}
