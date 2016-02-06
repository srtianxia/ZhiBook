package com.srtianxia.zhibook.utils.http;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.srtianxia.zhibook.app.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by srtianxia on 2016/1/22.
 */
public class HttpUtils {
    private static final String TAG = "HttpUtils";
    public static String postFromNet(String url,String post){
            HttpURLConnection conn = null;
            try {

                URL mURL = new URL(url);

                conn = (HttpURLConnection) mURL.openConnection();

                conn.setRequestMethod("POST");
                conn.setReadTimeout(5000);
                conn.setConnectTimeout(10000);
                conn.setDoOutput(true);

                // post请求的参数
                String data = post;

                OutputStream out = conn.getOutputStream();
                out.write(data.getBytes());
                out.flush();
                out.close();

                int responseCode = conn.getResponseCode();
                Log.d("HttpUtils post:","responseCode"+responseCode);
                if (responseCode == 200) {

                    InputStream is = conn.getInputStream();
                    String response = getStringFromInputSteam(is);
                    return response;
                } else {
                    throw new NetworkErrorException("response status is "+responseCode);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }

            return null;
        }
    public static String getFromNet(String url){
        InputStream inputStream = getHttpInputSteam(url);
        try {
            String response = getStringFromInputSteam(inputStream);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static InputStream getHttpInputSteam(String url){
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
                return inputStream;
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

    private static String getStringFromInputSteam(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine())!=null){
            builder.append(line);
        }
        return builder.toString();
    }

    class RequsetResult{
        private InputStream requestInputStream;
        private int responseCode;

        public RequsetResult(InputStream requestInputStream,int responseCode){
            this.requestInputStream = requestInputStream;
            this.responseCode = responseCode;
        }
    }
}
