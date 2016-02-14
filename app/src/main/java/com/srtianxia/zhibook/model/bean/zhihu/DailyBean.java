package com.srtianxia.zhibook.model.bean.zhihu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srtianxia on 2016/1/23.
 */
public class DailyBean {
    private String date;
    private List<Story> stories = new ArrayList<Story>();
    private List<TopStory> top_stories = new ArrayList<TopStory>();

    /**
     *
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     *     The stories
     */
    public List<Story> getStories() {
        return stories;
    }

    /**
     *
     * @param stories
     *     The stories
     */
    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    /**
     *
     * @return
     *     The topStories
     */
    public List<TopStory> getTopStories() {
        return top_stories;
    }

    /**
     *
     * @param topStories
     *     The top_stories
     */
    public void setTopStories(List<TopStory> topStories) {
        this.top_stories = topStories;
    }
}
