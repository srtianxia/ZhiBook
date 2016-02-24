package com.srtianxia.zhibook.presenter;

import com.srtianxia.zhibook.model.Imodel.IZhiHuModel;
import com.srtianxia.zhibook.model.ZhiHuModel;
import com.srtianxia.zhibook.model.bean.zhihu.ThemeDailyBean;
import com.srtianxia.zhibook.model.callback.OnGetThemeListener;
import com.srtianxia.zhibook.view.IView.IFragmentThemeDaily;

/**
 * Created by srtianxia on 2016/2/24.
 */
public class FragmentThemeDailyPresenter {
    private IFragmentThemeDaily iFragmentThemeDaily;
    private IZhiHuModel iZhiHuModel;

    public FragmentThemeDailyPresenter(IFragmentThemeDaily iFragmentThemeDaily){
        this.iFragmentThemeDaily = iFragmentThemeDaily;
        iZhiHuModel = ZhiHuModel.getInstance();
    }

    public void getThemeList(){
        iZhiHuModel.getThemeList(new OnGetThemeListener() {
            @Override
            public void success(ThemeDailyBean bean) {
                iFragmentThemeDaily.showThemeList(bean);
            }

            @Override
            public void failure(String s) {

            }
        });
    }
}
