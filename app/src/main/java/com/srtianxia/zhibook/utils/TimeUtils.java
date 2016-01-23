package com.srtianxia.zhibook.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by srtianxia on 2016/1/17.
 */

public class TimeUtils {
    private static final Date getCurrentTime() {
        return new Date(System.currentTimeMillis());
    }

    public static String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd  HH:mm");
        return simpleDateFormat.format(getCurrentTime());
    }
}
