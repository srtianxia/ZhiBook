
package com.srtianxia.zhibook.model.bean.zhihu;

import java.util.ArrayList;
import java.util.List;

public class ThemeDailyBean {

    private Integer limit;
    private List<Object> subscribed = new ArrayList<Object>();
    private List<ThemeDaily> others = new ArrayList<>();

    /**
     * 
     * @return
     *     The limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * 
     * @param limit
     *     The limit
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * 
     * @return
     *     The subscribed
     */
    public List<Object> getSubscribed() {
        return subscribed;
    }

    /**
     * 
     * @param subscribed
     *     The subscribed
     */
    public void setSubscribed(List<Object> subscribed) {
        this.subscribed = subscribed;
    }

    public List<ThemeDaily> getOthers() {
        return others;
    }

    public void setOthers(List<ThemeDaily> others) {
        this.others = others;
    }
}
