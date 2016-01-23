package com.srtianxia.zhibook.app;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srtianxia on 2016/1/21.
 */
public class ActivityCollector {
    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    public static void removeAcitvity(Activity activity){
        activityList.remove(activity);
    }

    public static void finishActivitis(){
        for (Activity activity:activityList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
