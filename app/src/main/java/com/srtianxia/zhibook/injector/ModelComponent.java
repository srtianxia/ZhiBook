package com.srtianxia.zhibook.injector;



import com.srtianxia.zhibook.model.FileModel;
import com.srtianxia.zhibook.presenter.MusicPlayerPresenter;

import dagger.Component;

/**
 * Created by srtianxia on 2016/3/5.
 */
@Component(modules = FileModel.class)
public interface ModelComponent {
    void inject(MusicPlayerPresenter presenter);
}
