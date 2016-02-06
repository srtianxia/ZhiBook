package com.srtianxia.zhibook.model.bean.zhihu;

import java.util.List;

/**
 * Created by srtianxia on 2016/1/23.
 */
public class Story {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private String title;
    private List<String> images;
    private int type;
}
