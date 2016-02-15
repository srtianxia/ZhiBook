package com.srtianxia.zhibook.utils.cache;

import android.content.SharedPreferences;

import com.srtianxia.zhibook.app.APP;

/**
 * Created by srtianxia on 2016/2/16.
 */
public class SharedPreferenceUtils {
    public static void set(String token){
        SharedPreferences.Editor editor = APP.getContext().getSharedPreferences("data",APP.getContext().MODE_PRIVATE).edit();
        editor.putString("token",token);
        editor.commit();
    }
    public static String get(){
        SharedPreferences preferences = APP.getContext().getSharedPreferences("data",APP.getContext().MODE_PRIVATE);
        String token = preferences.getString("token","");
        return token;
    }
}
