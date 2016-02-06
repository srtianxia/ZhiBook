package com.srtianxia.zhibook.model.bean.zhihu;

import java.util.List;

/**
 * Created by srtianxia on 2016/1/23.
 */
public class DailyBean {
    private String date;
    private List<Story> stories;
    private List<BannerData> top_stories;

    public List<BannerData> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<BannerData> top_stories) {
        this.top_stories = top_stories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }
}
