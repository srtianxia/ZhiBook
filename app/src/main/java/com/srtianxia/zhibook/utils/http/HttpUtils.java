package com.srtianxia.zhibook.utils.http;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.srtianxia.zhibook.app.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by srtianxia on 2016/1/22.
 */
public class HttpUtils {
    public static String getFromNet(String url){
        HttpURLConnection connection = null;
        try {
            URL mUrl = new URL(url);
            connection = (HttpURLConnection) mUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(10000);

            int responseCode = connection.getResponseCode();

            if (responseCode == API.httpCodeOk){
                InputStream inputStream = connection.getInputStream();
                String response = getStringFromInputSteam(inputStream);
                return response;
            }else {
                throw new NetworkErrorException("response status is "+responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }
    private static String getStringFromInputSteam(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine())!=null){
            builder.append(line);
        }
        return builder.toString();
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
