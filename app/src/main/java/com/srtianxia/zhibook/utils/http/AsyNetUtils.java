package com.srtianxia.zhibook.utils.http;

import android.os.Handler;

import com.srtianxia.zhibook.utils.http.callback.NetUtilsCallback;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用线程池封装的异步get/post请求
 * Created by srtianxia on 2016/1/23.
 * 应该再考虑下线程池各个参数的大小
 */
public class AsyNetUtils {
    private static final String TAG = "AsyNetUtils";
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final long KEEP_ALIVE = 10L;

    private static final ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger count = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"AsyNetUtils #"+count);
        }
    };

    private static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
            KEEP_ALIVE, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(), threadFactory);

    public static void getRequest(final String url, final NetUtilsCallback callback){
        final Handler handler = new Handler();
        Runnable getRunnableTask = new Runnable() {
            @Override
            public void run() {
                final String response = HttpUtils.getFromNet(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(response);
                    }
                });
            }
        };
        THREAD_POOL_EXECUTOR.execute(getRunnableTask);
    }

    public static void postRequest(final String url, final String postContent, final NetUtilsCallback callback){
        final Handler handler = new Handler();
        Runnable postRunnableTask = new Runnable() {
            @Override
            public void run() {
                final String response = HttpUtils.postFromNet(url,postContent);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(response);
                    }
                });
            }
        };
        THREAD_POOL_EXECUTOR.execute(postRunnableTask);
    }
}
