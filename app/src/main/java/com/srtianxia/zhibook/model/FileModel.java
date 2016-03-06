package com.srtianxia.zhibook.model;

import com.srtianxia.zhibook.model.callback.OnFileListener;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by srtianxia on 2016/2/17.
 */
@Module
public class FileModel {
    @Inject public FileModel(){}

    @Provides
    public String start(OnFileListener listener){
        //模拟请求
        if (true){
            listener.success("播放");
        }else {
            listener.failure("");
        }
        return null;
    }

    @Provides
    public String pause(OnFileListener listener){
        if (true){
            listener.success("暂停");
        }else {
            listener.failure("");
        }
        return null;
    }
}
