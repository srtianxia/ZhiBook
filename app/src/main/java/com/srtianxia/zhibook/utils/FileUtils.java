package com.srtianxia.zhibook.utils;

import android.os.Environment;

/**
 * Created by srtianxia on 2016/2/17.
 */
public class FileUtils {
    public static boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
