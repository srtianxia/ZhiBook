package com.srtianxia.zhibook.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;

import com.srtianxia.zhibook.app.APP;

/**
 * Created by srtianxia on 2016/2/16.
 */
public class SharedPreferenceUtils {
    public static void set(String token,String authorId,String headUrl,String name){
        SharedPreferences.Editor editor = APP.getContext().getSharedPreferences("ZhiBookSp",APP.getContext().MODE_PRIVATE).edit();
        editor.putString("token",token);
        editor.putString("authorId",authorId);
        editor.putString("headUrl",headUrl);
        editor.putString("name",name);
        editor.commit();
    }
    public static String getToken(){
        SharedPreferences preferences = APP.getContext().getSharedPreferences("ZhiBookSp",APP.getContext().MODE_PRIVATE);
        String token = preferences.getString("token","");
        return token;
    }

    public static String getId(){
        SharedPreferences preferences = APP.getContext().getSharedPreferences("ZhiBookSp",APP.getContext().MODE_PRIVATE);
        String id = preferences.getString("authorId","");
        return id;
    }

    public static String gethead(){
        SharedPreferences preferences = APP.getContext().getSharedPreferences("ZhiBookSp",APP.getContext().MODE_PRIVATE);
        String headUrl = preferences.getString("headUrl","");
        return headUrl;
    }

    public static String getName(){
        SharedPreferences preferences = APP.getContext().getSharedPreferences("ZhiBookSp",APP.getContext().MODE_PRIVATE);
        String name = preferences.getString("name","");
        return name;
    }

    public static void clear() {
        SharedPreferences sp = APP.getContext().getSharedPreferences("ZhiBookSp", APP.getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    public static void changeHead(String url){
        SharedPreferences.Editor editor = APP.getContext().getSharedPreferences("ZhiBookSp",APP.getContext().MODE_PRIVATE).edit();
        editor.putString("headUrl",url);
        editor.commit();
    }
}
