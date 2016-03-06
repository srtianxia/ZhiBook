package com.srtianxia.zhibook.presenter;

import com.srtianxia.zhibook.injector.DaggerModelComponent;
import com.srtianxia.zhibook.injector.ModelComponent;
import com.srtianxia.zhibook.model.FileModel;
import com.srtianxia.zhibook.model.callback.OnFileListener;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by srtianxia on 2016/3/6.
 */

public class MusicPlayerPresenter {
    private IView iView;
    private ModelComponent component;
    @Inject
    FileModel model;

    public MusicPlayerPresenter(IView iView){
        this.iView = iView;
        component = DaggerModelComponent.builder().fileModel(new FileModel()).build();
        component.inject(this);
    }

    public void start(){
        model.start(new OnFileListener() {
            @Override
            public void success(String s) {
                iView.showMessage(s);
            }

            @Override
            public void failure(String s) {

            }
        });
    }

    public void pause(){
        model.pause(new OnFileListener() {
            @Override
            public void success(String s) {
                iView.showMessage(s);
            }

            @Override
            public void failure(String s) {

            }
        });
    }

    public static interface IView{
        void showMessage(String s);
    }
}
