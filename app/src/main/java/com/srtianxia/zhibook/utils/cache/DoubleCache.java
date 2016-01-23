package com.srtianxia.zhibook.utils.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.LruCache;

import com.srtianxia.zhibook.app.APP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by srtianxia on 2016/1/22.
 * 1/23 00:31 DoubleCache 整个类尚未调试使用
 * 完成到 DiskLruCache 缓存文件读取 LruCache 读取/写入
 * 未完成 DiskLruCache 缓存写入
 *
 *  ps：使用前要再次调试逻辑
 */
public class DoubleCache implements ImageCache {
    private LruCache<String,Bitmap> memoryCache;
    private DiskLruCache diskLruCache;

    private static final long DISK_CACHE_SIZE = 1024 * 1024 * 50;
    private static final int DISK_CACHE_INDEX = 0;

    private DoubleCache(){
        int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
        int cacheSize = maxMemory/8;
        memoryCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
        File diskCacheDir = getDiskCacheDir("bitmap");
        if (!diskCacheDir.exists()){
            diskCacheDir.mkdirs();
        }
        if (getUsableSpace(diskCacheDir)>DISK_CACHE_SIZE){
            try {
                diskLruCache = DiskLruCache.open(diskCacheDir,1,1,DISK_CACHE_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static DoubleCache builder(){
        return new DoubleCache();
    }

    @Override
    public Bitmap get(String url) {
        String cacheKey = hashKeyFormUrl(url);
        Bitmap bitmap = loadBitmapFromMemCache(cacheKey);
        if(bitmap!=null){
            return bitmap;
        }
        try {
            bitmap = loadBitmapFromDiskCache(cacheKey);
            if (bitmap!=null){
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap loadBitmapFromDiskCache(String cacheKey) throws IOException {
        Bitmap bitmap = null;
        DiskLruCache.Snapshot snapShot = diskLruCache.get(cacheKey);
        FileInputStream is = (FileInputStream) snapShot.getInputStream(DISK_CACHE_INDEX);
        bitmap = BitmapFactory.decodeStream(is);
        if (bitmap != null) {
            memoryCache.put(cacheKey,bitmap);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        String cacheKey = hashKeyFormUrl(url);
        if (memoryCache.get(cacheKey)==null) {
            memoryCache.put(cacheKey, bitmap);
        }
    }

    private Bitmap loadBitmapFromMemCache(String cacheKey){
        Bitmap bitmap = memoryCache.get(cacheKey);
        return bitmap;
    }
    private File getDiskCacheDir(String uniqueName) {
        boolean externalStorageAvailable = Environment
                .getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        final String cachePath;
        if (externalStorageAvailable) {
            cachePath = APP.getContext().getExternalCacheDir().getPath();
        } else {
            cachePath = APP.getContext().getCacheDir().getPath();
        }

        return new File(cachePath + File.separator + uniqueName);
    }

    private long getUsableSpace(File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return path.getUsableSpace();
        }
        final StatFs stats = new StatFs(path.getPath());
        return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
    }

    private String hashKeyFormUrl(String url) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

}
